package com.example.lab7;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
//        TextView textView = findViewById(R.id.text_view);
//
//        textView.setText(ServerCommands.downloadJSONUsingHTTPGetRequest());
//        );
        refreshDB();
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