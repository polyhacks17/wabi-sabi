package com.polyhacks.kintsugi;

import android.app.Application;

/**
 * Created by Caleb on 2/24/2017.
 */

public class doTheCreate extends Application {
    JSONHandler serialKiller;
    @Override
    public void onCreate(){
        try {
            serialKiller = JSONHandler.getInstance();
            serialKiller.updateJSON();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
