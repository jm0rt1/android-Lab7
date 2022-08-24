package com.example.lab7;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder {


    TextView title;
    TextView content;
    View view;

    public PostViewHolder(View view)
    {
        super(view);
        title = view.findViewById(R.id.post_content);
        content = view.findViewById(R.id.post_title);
        this.view = view;
    }


}
