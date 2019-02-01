package com.example.tbte4.exjobb.helpers;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpLoader extends AsyncTask<String, Void, JSONObject> {

    private static String tag = "ExJobb HttpLoader";

    protected JSONObject doInBackground(String... url) {
        HttpURLConnection conn = null;
        JSONObject json = null;
        try {
            // Create URL
            URL endpoint = new URL(url[0]);

            // Create connection
            conn = (HttpURLConnection) endpoint.openConnection();

            conn.setRequestProperty("User-Agent", "exjobb-rest-app-v0.1");

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

}