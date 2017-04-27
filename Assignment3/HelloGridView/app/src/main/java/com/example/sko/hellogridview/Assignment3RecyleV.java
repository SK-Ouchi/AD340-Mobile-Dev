package com.example.sko.hellogridview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class Assignment3RecyleV extends AppCompatActivity {

    RecyclerView recyclerView;

    Context context;

    RecyclerView.Adapter recyclerView_Adapter;

    RecyclerView.LayoutManager recyclerViewLayoutManager;

    String[] numbers = {
            "Hello", "Goodbye",
            "Up", "Down",
            "In", "Out",
            "Low", "High",
            "Zero", "One",
            "Black", "White",
            "Day","Night",
            "Rich","Broke as all hell",
            "Pass","Fail",
            "X","O",
            "A","Z",
            "You","Me",
            "Good","Bad",
            "Test of a single card"

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment3_recyle_v);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);

        //Change 2 to your choice because here 2 is the number of Grid layout Columns in each row.
        recyclerViewLayoutManager = new GridLayoutManager(context, 2);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerView_Adapter = new RecyclerViewAdapter(context,numbers);

        recyclerView.setAdapter(recyclerView_Adapter);

    }
}