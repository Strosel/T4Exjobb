package com.example.tbte4.exjobb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar myToolbar = findViewById(R.id.menu_toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        int id = intent.getIntExtra("locationID", 0);

        if (id == 0) {
            Log.v("ExJobb", "Didn't get id");
            Toast toast = Toast.makeText(this, R.string.err_conn, Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        String BaseURL = getString(R.string.base_url);
        HttpLoader loader = new HttpLoader();
        try {
            JSONObject json = loader.execute(BaseURL+"/location?id="+ String.valueOf(id)).get();
            ArrayList<FoodItem> foodItems = FoodItem.FoodItemListBuilder(json.getJSONArray("menu"));

            RecyclerView foodrecycler = findViewById(R.id.foodMenu);
            foodrecycler.setLayoutManager(new GridLayoutManager(this, 2));
            FoodItemAdapter adapter = new FoodItemAdapter(this, foodItems);
            foodrecycler.setAdapter(adapter);
        } catch (Exception e) {
            Log.v("ExJobb", e.toString());
            Toast toast = Toast.makeText(this, R.string.err_conn, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_cart) {
            Toast.makeText(this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
