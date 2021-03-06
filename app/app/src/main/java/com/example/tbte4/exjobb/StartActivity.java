package com.example.tbte4.exjobb;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tbte4.exjobb.adapters.LocationsAdapter;
import com.example.tbte4.exjobb.helpers.HttpLoader;
import com.example.tbte4.exjobb.helpers.cacheFileHelper;
import com.example.tbte4.exjobb.interfaces.HttpFinishedInterface;
import com.example.tbte4.exjobb.items.Location;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class StartActivity extends AppCompatActivity {

    private static String tag = "ExJobb StartActivity";
    private SwipeRefreshLayout swipeContainer;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar myToolbar = findViewById(R.id.start_toolbar);
        setSupportActionBar(myToolbar);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadLocations();
            }
        });

        spinner = findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        loadLocations();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    public HttpFinishedInterface renderLocations = (JSONObject input) -> {
        try {
            //add animated loading ring
            ArrayList<Location> locations = Location.LocationListBuilder(input.getJSONArray("locations"));

            RecyclerView locationrecycler = findViewById(R.id.locationrecycler);
            locationrecycler.setLayoutManager(new LinearLayoutManager(this));
            LocationsAdapter adapter = new LocationsAdapter(this, locations);
            locationrecycler.setAdapter(adapter);
        } catch (Exception e) {
            Log.v(tag, e.toString());
            Toast toast = Toast.makeText(this, R.string.err_conn, Toast.LENGTH_SHORT);
            toast.show();
        }

        swipeContainer.setRefreshing(false);
        spinner.setVisibility(View.GONE);
    };

    public void loadLocations() {
        cacheFileHelper cacheCreator = new cacheFileHelper(this, R.string.cache_file_name);
        cacheCreator.save(new JSONObject());

        String BaseURL = getString(R.string.server_endpoint);
        HttpLoader loader = new HttpLoader(this.renderLocations);
        loader.execute(BaseURL+"/locations");
    }
}
