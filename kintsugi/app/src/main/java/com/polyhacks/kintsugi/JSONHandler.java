package com.polyhacks.kintsugi;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.google.gson.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

// JSONHandler -- written by Caleb
// It does cool stuffs

public class JSONHandler {

    private String URL = "https://raw.githubusercontent.com/r3pwn/PolyHacks2017/master/JSON/prod.json"; // github string goes here
    private JsonObject JSON = new JsonParser().parse("{\"title\":\"It hasn\'t loaded the object\", \"schedule\": [{\"title\": \"blank\"}], \"announcements\": [{\"title\": \"blank\"}]}").getAsJsonObject();
    private static JSONHandler master = null;

    private JSONHandler(){
    }

    public static JSONHandler getInstance() throws Exception{
        if (master == null) {
            master = new JSONHandler();
        }
        return master;
    }

    public void updateJSON(){
        /*
        StringBuilder result = new StringBuilder();
        URL url = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        */
        try {
            new getJSON().execute().get(3000, TimeUnit.MILLISECONDS);
        } catch (Exception e)
        {
            e.printStackTrace();
            Log.d("JSONHandler", "Something went wrong here, might wanna check it out.");
        }
    }

    public String getTitle(){
        return JSON.get("title").toString().replaceAll("^\"|\"$", "");
    }

    public ArrayList getSchedule(){
        return JSONtoHashmap("schedule");
    }

    public ArrayList getAnnouncements(){
        return JSONtoHashmap("announcements");
    }

    private ArrayList JSONtoHashmap(String member){
        JsonArray tempJSONArray = JSON.getAsJsonArray(member);
        ArrayList tempArray = new ArrayList();

        for (int i = 0; i < tempJSONArray.size(); i++) {
            HashMap<String, String> output = new HashMap<>();
            String stringJSON = tempJSONArray.get(i).toString();
            stringJSON = stringJSON.replace("{", "");
            stringJSON = stringJSON.replace("}", "");
            stringJSON = stringJSON.replace("\":\"", "\"&\"");
            stringJSON = stringJSON.replaceAll(", ", "%%%%");
            String[] test = stringJSON.split(",");

            for (int j = 0; j < test.length; j++) {
                Log.d("KINTSUGI", "String: " + test[j]);
                test[j] = test[j].replaceAll("%%%%", ", ");
                String[] temp = test[j].split("&");
                temp [0] = temp[0].replaceAll("^\"|\"$", "");
                temp [1] = temp[1].replaceAll("^\"|\"$", "");
                output.put(temp[0], temp[1]);
            }
            tempArray.add(output);
        }
        return tempArray;
    }

    public void setJSON(String JSONString){
        try {
            JSON = new JsonParser().parse(JSONString).getAsJsonObject();
        } catch (java.lang.IllegalStateException e)
        {
            JSON = new JsonParser().parse("{\"title\":\"It hasn\'t loaded the object\", \"schedule\": [{\"title\": \"blank\"}], \"announcements\": [{\"title\": \"blank\"}]}").getAsJsonObject();
        }
    }

    private class getJSON extends AsyncTask<String, Void, String>
    {
        private String URL = "https://raw.githubusercontent.com/r3pwn/PolyHacks2017/master/JSON/prod.json";
        private String JSONstring = null;


        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();
            URL url = null;
            try {
                url = new URL(URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }

            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            JSONHandler temp = null;
            try {
                temp = JSONHandler.getInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            temp.setJSON(result);
        }

    }
}