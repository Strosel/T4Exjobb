package com.example.tbte4.exjobb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    String BaseURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        BaseURL = getString(R.string.base_url);
        HttpLoader loader = new HttpLoader();
        try {
            JSONObject json = loader.execute(BaseURL+"/locations").get();
            ArrayList<Location> locations = Location.LocationListBuilder(json);

            RecyclerView locationrecycler = findViewById(R.id.locationrecycler);
            locationrecycler.setLayoutManager(new LinearLayoutManager(this));
            LocationsAdapter adapter = new LocationsAdapter(this, locations);
            locationrecycler.setAdapter(adapter);
        } catch (Exception e) {
            // toast cant connect to host
            Log.v("ExJobb", e.toString());
        }
    }
}
