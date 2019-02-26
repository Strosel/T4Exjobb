package com.example.tbte4.exjobb;

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
import com.example.tbte4.exjobb.helpers.cacheFileHelper;
import com.example.tbte4.exjobb.items.FoodItem;

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

    public void buybttnPressed(Button bttn) {

    }
}
