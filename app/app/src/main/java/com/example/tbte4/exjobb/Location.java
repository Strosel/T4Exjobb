package com.example.tbte4.exjobb;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Location {

    protected int id;
    protected  String Name;
    protected double Lat, Long;
    protected boolean isonline;
    protected ArrayList<FoodItem> menu;

    public Location(JSONObject json) {
        try {
            id = json.getInt("id");
            Name = json.getString("name");
            Lat = json.getDouble("latitude");
            Long = json.getDouble("longitude");
            isonline = json.getBoolean("isOnline");
            //menu  "menu";
        } catch (Exception e) {}
    }

    public static ArrayList<Location> LocationListBuilder(JSONArray json) {
        ArrayList<Location> out = new ArrayList<>();
        try {
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                out.add(new Location(obj));
            }
        } catch (Exception e) {}
        return out;
    }
}
