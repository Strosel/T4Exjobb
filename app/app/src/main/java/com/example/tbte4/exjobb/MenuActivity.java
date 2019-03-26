package com.example.tbte4.exjobb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tbte4.exjobb.adapters.FoodItemAdapter;
import com.example.tbte4.exjobb.helpers.HttpLoader;
import com.example.tbte4.exjobb.items.FoodItem;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuActivity extends AppCompatActivity  {

    private static String tag = "ExJobb MenuActivity";

    int id;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar myToolbar = findViewById(R.id.menu_toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        id = intent.getIntExtra("locationID", 0);

        if (id == 0) {
            Log.v(tag, "Didn't get id");
            Toast toast = Toast.makeText(this, R.string.err_conn, Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

    }

    @Override
    protected void onResume() {
        spinner = findViewById(R.id.menu_spinner);
        spinner.setVisibility(View.VISIBLE);

        String BaseURL = getString(R.string.server_endpoint);
        HttpLoader loader = new HttpLoader((JSONObject input) -> {
            try {
                ArrayList<FoodItem> foodItems = FoodItem.FoodItemListBuilder(input.getJSONArray("menu"));

                RecyclerView foodrecycler = findViewById(R.id.foodMenu);
                foodrecycler.setLayoutManager(new GridLayoutManager(this, 2));
                FoodItemAdapter adapter = new FoodItemAdapter(this, foodItems);
                foodrecycler.setAdapter(adapter);
            } catch (Exception e) {
                Log.v(tag, e.toString());
                Toast toast = Toast.makeText(this, R.string.err_conn, Toast.LENGTH_SHORT);
                toast.show();
            }

            spinner.setVisibility(View.GONE);
        });

        loader.execute(BaseURL+"/location?id="+ String.valueOf(id));

        super.onResume();
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
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
