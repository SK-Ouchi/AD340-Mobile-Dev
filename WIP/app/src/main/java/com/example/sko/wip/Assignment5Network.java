package com.example.sko.wip;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.Collections;
import java.util.List;

/**
 * Created by Skouchi on 5/11/2017.
 */

public class Assignment5Network extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataList> data = Collections.emptyList();
    DataList current;
    int currentPos = 0;

    // create constructor to innitilize context and data sent from MainActivity
    public Assignment5Network(Context context, List<DataList> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_assignment5_network, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        DataList current = data.get(position);
        myHolder.textNumName.setText(current.numName);
        myHolder.textNext.setText("Next: " + current.numNext);

        // load image into imageview using glide
        Glide.with(context).load("http://souchi.icoolshow.net/android/images/" + current.numImage)
                .placeholder(R.drawable.ic_img_error)
                .error(R.drawable.ic_img_error)
                .into(myHolder.ivNum);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView textNumName;
        ImageView ivNum;
        TextView textNext;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textNumName = (TextView) itemView.findViewById(R.id.textNumName);
            ivNum = (ImageView) itemView.findViewById(R.id.ivNum);
            textNext = (TextView) itemView.findViewById(R.id.next);
        }

    }



}




