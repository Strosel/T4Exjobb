package com.example.tbte4.exjobb.helpers;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.tbte4.exjobb.interfaces.HttpFinishedInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpLoader extends AsyncTask<String, Void, JSONObject> {

    private static String tag = "ExJobb HttpLoader";
    private HttpFinishedInterface finished;

    private boolean isPost = false;
    private String postBody = "";

    public HttpLoader(HttpFinishedInterface finished) {
        this.finished = finished;
    }

    public void Post(Object body) {
        isPost = true;
        postBody = body.toString();
    }

    protected JSONObject doInBackground(String... url) {
        HttpURLConnection conn = null;
        JSONObject json = null;
        try {
            // Create URL
            URL endpoint = new URL(url[0]);

            // Create connection
            conn = (HttpURLConnection) endpoint.openConnection();

            conn.setRequestProperty("User-Agent", "exjobb-rest-app-v0.1");

            if (isPost) {
                conn.setRequestMethod("POST");
                conn.setRequestProperty( "Content-Type", "application/json");
                conn.setRequestProperty( "charset", "utf-8");

                DataOutputStream wr = new DataOutputStream( conn.getOutputStream());
                wr.writeBytes(postBody);
            }

            if (conn.getResponseCode() == 200) {
                InputStream responseBody = conn.getInputStream();

                String text = null;
                try (Scanner scanner = new Scanner(responseBody, "UTF-8")) {
                    text = scanner.useDelimiter("\\n").next();
                }

                json = new JSONObject(text);
            } else {
                throw new Exception("Connection not OK, HTTP RespsonseCode: " + conn.getResponseCode());
            }


        } catch (Exception e) {
            Log.v(tag, e.toString());
        }

        if (conn != null)
            conn.disconnect();
        return json;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        finished.Finished(result);
    }

}