package com.example.tbte4.exjobb.helpers;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.JsonReader;
import android.util.Log;

import com.example.tbte4.exjobb.R;
import com.example.tbte4.exjobb.items.FoodItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class cacheFileHelper {

    private static String tag = "ExJobb cacheFileHelper";

    private Context ctx;
    private File file;
    private JSONObject json;

    public cacheFileHelper(Context ctx, int R_id) {
        this.ctx = ctx;

        String name = ctx.getString(R_id);

        String fileName = Uri.parse(name).getLastPathSegment();
        file = new File(ctx.getCacheDir(), fileName);
        if (!file.exists()) {
            try {
                // String fileName = Uri.parse(name).getLastPathSegment();
                file = File.createTempFile(fileName, null, ctx.getCacheDir());
            } catch (IOException e) {
                Log.v(tag, e.toString());
            }
        }

    }

    public boolean save() {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            if (json != null) {
                fos.write(json.toString().getBytes());
            }
            fos.close();
            return true;
        } catch (Exception e) {
            Log.v(tag, e.toString());
            return false;
        }
    }

    public boolean save(JSONObject json_in) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            if (json_in != null) {
                fos.write(json_in.toString().getBytes());
            }
            fos.close();
            return true;
        } catch (Exception e) {
            Log.v(tag, e.toString());
            return false;
        }
    }

    //WARNING a nonsucessful load would give a empty json object and therefore overwrite any existing data on a save
    public boolean load() {
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String jstring = sb.toString();
            json = new JSONObject(jstring);
            return true;
        } catch (Exception e) {
            Log.v(tag, e.toString());
            json = new JSONObject();
            return false;
        }
    }

    public void setLocationID(int id) throws JSONException {
        json.put("location", id);
    }

    public int getLocationID() throws JSONException {
        int v = json.getInt("location");
        return v;
    }

    public void setOrder(int id, int volume) throws JSONException {
        String sid = String.valueOf(id);
        json.put(sid, volume);
    }

    public int getOrder(int id) throws JSONException {
        String sid = String.valueOf(id);
        if (json.has(sid)) {
            int v = json.getInt(sid);
            return v;
        } else {
            return -1;
        }
    }

    //remove order
    public void remOrder(int id) {
        String sid = String.valueOf(id);
        json.remove(sid);
    }

    //increment order
    public void incOrder(int id) throws JSONException {
        int vol = this.getOrder(id);
        if (vol != -1)
            this.setOrder(id, vol+1);
        else
            this.setOrder(id, 1);
    }

    //decrement order
    public void decOrder(int id) throws JSONException {
        int vol = this.getOrder(id);
        if (vol > 1)
            this.setOrder(id, vol-1);
        else {
            this.remOrder(id);
        }
    }

    public ArrayList<FoodItem> getCart() throws ExecutionException, InterruptedException, JSONException {
        String req = "";
        Iterator<String> keys = json.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            req += "key=" + key + "&";
        }
        if (req.equals(""))
            return new ArrayList<FoodItem>();
        String BaseURL = ctx.getString(R.string.server_endpoint);
        HttpLoader loader = new HttpLoader();
        JSONObject json = loader.execute(BaseURL+"/MenuItems?"+ req).get();
        JSONArray arr = json.getJSONArray("menu");
        return FoodItem.FoodItemListBuilder(arr);
    }

    public boolean clear() {
        return this.save(new JSONObject());
    }
}
