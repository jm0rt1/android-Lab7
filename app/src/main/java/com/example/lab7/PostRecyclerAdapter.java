package com.example.lab7;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostViewHolder>{
    public static String TAG = "PostRecyclerAdapter";
    ArrayList<String[]> data;
    Context c;

    public PostRecyclerAdapter(Context c, ArrayList<String[]> list){
        data = list;
        this.c = c;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.post_card,parent,false);
        PostViewHolder postViewHolder = new PostViewHolder(v);
        return postViewHolder;

    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.title.setText(data.get(position)[0]);
        holder.content.setText(data.get(position)[1]);
        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(TAG, "clicked");
            }
        });
    }



    @Override
    public int getItemCount() {
        return data.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
