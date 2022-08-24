package com.example.lab7;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NewPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);


        setSupportActionBar(findViewById(R.id.custom_toolbar));
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addNewPost(View v){
        ServerInterface.Posts.sendPost(1,
                findViewById(R.id.post_title).toString(),
                findViewById(R.id.post_content).toString(),
                getApplicationContext());
        MainActivity activity =(MainActivity)getParent();
        activity.refreshRecyclerView();
        finish();
    }

}