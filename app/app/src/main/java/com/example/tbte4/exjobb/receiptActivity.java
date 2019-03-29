package com.example.tbte4.exjobb;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.tbte4.exjobb.helpers.cacheFileHelper;

import java.util.concurrent.TimeUnit;

public class receiptActivity extends AppCompatActivity {

    public int deadline;
    TextView deadlineTXT;

    String Tag = "ExJobb receiptActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        //make a home toolbar
        Toolbar myToolbar = findViewById(R.id.receipt_toolbar);
        setSupportActionBar(myToolbar);


        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        TextView idTXT = findViewById(R.id.recepit_id);
        idTXT.setText(String.valueOf(id));

        deadline = intent.getIntExtra("deadline", 0);
        deadlineTXT = findViewById(R.id.recepit_deadline);
        deadlineTXT.setText(this.millisToString(deadline));

        cacheFileHelper order = new cacheFileHelper(this, R.string.cache_file_name);
        order.load();
        order.clear();
        order.save();

        final receiptActivity owner = this;
        final Handler handler = new Handler();
        final int delay = 1000;
        handler.postDelayed(new Runnable(){
            public void run(){
                //do something
                owner.deadline -= delay;
                deadlineTXT.setText(owner.millisToString(owner.deadline));

                if (owner.deadline > 0)
                    handler.postDelayed(this, delay);
            }
        }, delay);
    }

    private static String millisToString(int deadline) {
        return String.format("%d min %d s",
                TimeUnit.MILLISECONDS.toMinutes(deadline),
                TimeUnit.MILLISECONDS.toSeconds(deadline) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(deadline))
        );
    }
}
