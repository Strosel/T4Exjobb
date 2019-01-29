package com.example.tbte4.exjobb;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Location {

    protected int id;
    protected  String Name;
    protected double Lat, Long;
    protected boolean isonline;
    protected ArrayList<MenuItem> menu;

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

    public static ArrayList<Location> LocationListBuilder(JSONObject json) {
        ArrayList<Location> out = new ArrayList<Location>();
        try {
            JSONArray arr = json.getJSONArray("locations");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                out.add(new Location(obj));
            }
        } catch (Exception e) {}
        return out;
    }
}
