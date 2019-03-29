package com.example.tbte4.exjobb;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tbte4.exjobb.adapters.CartAdapter;
import com.example.tbte4.exjobb.adapters.FoodItemAdapter;
import com.example.tbte4.exjobb.helpers.HttpLoader;
import com.example.tbte4.exjobb.helpers.cacheFileHelper;
import com.example.tbte4.exjobb.items.FoodItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private static String tag = "ExJobb CartActivity";

    private cacheFileHelper order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar myToolbar = findViewById(R.id.cart_toolbar);
        setSupportActionBar(myToolbar);

        RecyclerView cartrecycler = findViewById(R.id.cartrecycler);
        cartrecycler.setLayoutManager(new LinearLayoutManager(this));
        CartAdapter adapter = new CartAdapter(this);
        cartrecycler.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    public void buybttnPressed(View bttn) {
        order = new cacheFileHelper(this, R.string.cache_file_name);
        order.load();
        try {
            ArrayList<FoodItem> cart = order.getCart();
            JSONArray body = new JSONArray();
            body.put(order.getLocationID());
            for (int i = 0; i < cart.size(); i++) {
                int id = cart.get(i).id;
                body.put(id);
                body.put(order.getOrder(id));
            }

            HttpLoader loader = new HttpLoader((JSONObject input)->{

                //Clear cache in reciept

                Intent intent = new Intent(this, receiptActivity.class);
                try {
                    intent.putExtra("id", input.getInt("id"));
                    intent.putExtra("deadline", input.getInt("deadline"));
                    this.startActivity(intent);
                } catch (Exception e) {
                    Log.v(tag, e.toString());
                    Toast toast = Toast.makeText(this, R.string.err_order, Toast.LENGTH_SHORT);
                    toast.show();
                }

            });
            loader.Post(body);
            String BaseURL = getString(R.string.server_endpoint);
            loader.execute(BaseURL+"/order");
        } catch (Exception e) {
            Log.v(tag, e.toString());
            Toast toast = Toast.makeText(this, R.string.err_conn, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
