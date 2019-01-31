package com.example.tbte4.exjobb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    String BaseURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar myToolbar = findViewById(R.id.start_toolbar);
        setSupportActionBar(myToolbar);

        BaseURL = getString(R.string.base_url);
        HttpLoader loader = new HttpLoader();
        try {
            JSONObject json = loader.execute(BaseURL+"/locations").get();
            ArrayList<Location> locations = Location.LocationListBuilder(json.getJSONArray("locations"));

            RecyclerView locationrecycler = findViewById(R.id.locationrecycler);
            locationrecycler.setLayoutManager(new LinearLayoutManager(this));
            LocationsAdapter adapter = new LocationsAdapter(this, locations);
            locationrecycler.setAdapter(adapter);
        } catch (Exception e) {
            Log.v("ExJobb", e.toString());
            Toast toast = Toast.makeText(this, R.string.err_conn, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }
}
