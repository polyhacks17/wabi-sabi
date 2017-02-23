package com.polyhacks.kintsugi;

import com.google.gson.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

// JSONHandler -- written by Caleb
// It does cool stuffs

public class JSONHandler {

    private String URL = "https://raw.githubusercontent.com/r3pwn/PolyHacks2017/master/JSON/test.json"; // github string goes here
    private JsonObject JSON = new JsonParser().parse("{\"title\":\"It hasn\'t loaded the object\"}");
    private static JSONHandler master = null;

    private JSONHandler(){
    }

    public static JSONHandler getInstance() throws Exception{
        if (master == null) {
            master = new JSONHandler();
        }
        return master;
    }

    public void updateJSON() throws Exception{
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
        this.JSON = new JsonParser().parse(result.toString()).getAsJsonObject();
    }

    public String getTitle(){
        return JSON.get("title").toString();
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
            String[] test = stringJSON.split(",");

            for (int j = 0; j < test.length; j++) {
                String[] temp = test[j].split("&");
                output.put(temp[0], temp[1]);
            }
            tempArray.add(output);
        }
        return tempArray;
    }
}