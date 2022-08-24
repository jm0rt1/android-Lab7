package com.example.lab7;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Key;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;

public class ServerInterface {

    public static class Posts {
        private static String postsJsonCache = ""; // TODO: need to cache this in a better way for a bigger database
        private static long timeSinceLastUpdate = 0;

        private static class Keys {
            public static String TITLE ="title";
            public static String CONTENT = "content";
            public static String id = "id";
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private static void  guard(){
            //Call first in every method

            if ((Objects.equals(postsJsonCache, "") || timeSinceLastUpdate == 0)||(timeSinceLastUpdate+(1000)*60 > Instant.now().toEpochMilli())){
                postsJsonCache = ServerCommands.downloadJSONUsingHTTPGetRequest(ServerCommands.Urls.POSTS);
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public static ArrayList<String> getTitles() throws JSONException {
            guard();

            JSONArray jsonArray = new JSONArray(postsJsonCache);
            ArrayList<String> names = new ArrayList<String>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                names.add(obj.getString(Keys.TITLE));
            }
            return names;
        }


        @RequiresApi(api = Build.VERSION_CODES.O)
        public static ArrayList<String[]> getPost() throws JSONException {

            guard();

            JSONArray jsonArray = new JSONArray(postsJsonCache);
            ArrayList<String[]> names = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                String[] pair = new String[2];
                JSONObject obj = jsonArray.getJSONObject(i);
                pair[0] = obj.getString(Keys.TITLE);
                pair[1] = obj.getString(Keys.CONTENT);
                names.add(pair);

            }
            return names;
        }

        public static void sendPost(String title, String content){

        }


    }

}
