package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

    private void refreshDB()
    {
        String db_result = ServerCommands.downloadJSONUsingHTTPGetRequest(ServerCommands.Urls.USERS);
        String result = "";
        try
        {
            JSONArray jsonArray = new JSONArray(db_result);
            ArrayList<String> names = new ArrayList<String>();
            for(int i=0; i<jsonArray.length(); i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);
                names.add(obj.getString("name"));
            }
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