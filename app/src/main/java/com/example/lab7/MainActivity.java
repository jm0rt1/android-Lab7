package com.example.lab7;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    public static final String TAG = "MainActivity";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            RefreshRecyclerView();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        refreshDB();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void RefreshRecyclerView() throws JSONException {

        ArrayList<String[]> data = ServerInterface.Posts.getPosts();


        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);


        final PostRecyclerAdapter adapter = new PostRecyclerAdapter(this, data);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void refreshDB()
    {
        String result = "";
        try
        {
            ArrayList<String> names = ServerInterface.Posts.getTitles();
            ArrayAdapter nameadapter = new ArrayAdapter(this, R.layout.list_view_item, names);
            ListView listView = (ListView) findViewById(R.id.my_list_view);
            listView.setAdapter(nameadapter);

        }
        catch(Exception ex)
        {
            Log.d("JSONObject", "You had an exception");
            ex.printStackTrace();
        }
    }
}