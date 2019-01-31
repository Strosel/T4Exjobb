package com.example.tbte4.exjobb;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class FoodItem {

    protected int id;
    protected String Name;
    protected String Img;
    protected int Price;

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
