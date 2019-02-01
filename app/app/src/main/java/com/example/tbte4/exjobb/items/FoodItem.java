package com.example.tbte4.exjobb.items;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodItem {

    public int id;
    public String Name;
    public String Img;
    public int Price;

    public FoodItem(JSONObject json) {
        try {
            id = json.getInt("id");
            Name = json.getString("name");
            Img = json.getString("img");
            Price = json.getInt("price");
        } catch (Exception e) {}
    }

    public static ArrayList<FoodItem> FoodItemListBuilder(JSONArray json) {
        ArrayList<FoodItem> out = new ArrayList<>();
        try {
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                out.add(new FoodItem(obj));
            }
        } catch (Exception e) {}
        return out;
    }
}
