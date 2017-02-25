package com.polyhacks.kintsugi;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.CursorJoiner;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.transform.Result;

import static android.view.View.GONE;

public class ScheduleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    JSONHandler serialKiller;
    Boolean hasInternet = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // drawer.openDrawer(GravityCompat.START, false); // start with the drawer open

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_schedule);
        navigationView.setNavigationItemSelectedListener(this);

        try {
            serialKiller = JSONHandler.getInstance();
        } catch (Exception e) {
            //there is problem, plz fix
        }

        TextView tv = (TextView) findViewById(R.id.loadingtext);
        tv.setVisibility(GONE);

        ArrayList scheduleItemID = new ArrayList();

        int PREFERRED_TEXT_SIZE = 17; // in DiP
        float PREFERRED_PADDING_SIZE = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.content_schedule);
        ArrayList schedule = serialKiller.getSchedule();
        /*
            This code is special. Please don't touch. It will bite back, it is a flesh-eating demon straight from hell.
            DO NOT FEED AFTER MIDNIGHT.
         */
        Log.d("KINTSUGI", serialKiller.getTitle());
        if (serialKiller.getTitle().equalsIgnoreCase("It hasn't loaded the object"))
        {
            tv.setText("No internet access detected.");
            tv.setVisibility(View.VISIBLE);
            hasInternet = false;
        }

        if (hasInternet)
        {
            for(int i = 0; i < schedule.size(); i++) {
                final int itemNum = i;
                HashMap item = (HashMap) schedule.get(i);
                final String title = (String) item.get("title");
                final String time = (String) item.get("time");
                final String desc = (String) item.get("desc");
                final String ext_desc = (String) item.get("ext_desc");

                // declare and define the new relativelayout in which the textviews will reside
                RelativeLayout newrl = new RelativeLayout(ScheduleActivity.this);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                if (scheduleItemID.size() > 0 ) // if this is not the first item in the array
                {
                    params.addRule(RelativeLayout.BELOW, (int) scheduleItemID.get(i - 1));
                }
                newrl.setId(generateViewId());
                scheduleItemID.add(newrl.getId());

                // add time textview into the relativelayout
                TextView timeTv = new TextView(ScheduleActivity.this);
                RelativeLayout.LayoutParams timeParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                timeTv.setId(generateViewId());
                SpannableString spanString = new SpannableString(time);
                spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
                timeTv.setText(spanString);
                timeTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, PREFERRED_TEXT_SIZE);
                timeTv.setPadding(0, (int)PREFERRED_PADDING_SIZE, 0, 0);
                newrl.addView(timeTv, timeParams);

                // add title textview into the relativelayout
                TextView titleTv = new TextView(ScheduleActivity.this);
                RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                titleParams.addRule(RelativeLayout.RIGHT_OF, timeTv.getId());
                titleTv.setId(generateViewId());
                titleTv.setText(" - " + title);
                titleTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, PREFERRED_TEXT_SIZE);
                titleTv.setPadding(0, (int)PREFERRED_PADDING_SIZE, 0, 0);
                newrl.addView(titleTv, titleParams);

                // add desc textview into the relativelayout
                TextView descTv = new TextView(ScheduleActivity.this);
                RelativeLayout.LayoutParams descParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                descParams.addRule(RelativeLayout.BELOW, timeTv.getId());
                descTv.setId(generateViewId());
                descTv.setText("             " + desc);
                descTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, PREFERRED_TEXT_SIZE);
                descTv.setPadding(0, 0, 0, (int) PREFERRED_PADDING_SIZE);
                if (desc.equals(""))
                {
                    descTv.setVisibility(GONE);
                }
                newrl.addView(descTv, descParams);


                // horizontal line
                View horizontalLine = new View(ScheduleActivity.this);
                RelativeLayout.LayoutParams horizLineParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, 1);
                horizLineParams.addRule(RelativeLayout.BELOW, descTv.getId());
                horizontalLine.setBackgroundColor(Color.GRAY);
                newrl.addView(horizontalLine, horizLineParams);

                newrl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // make a dialog thingy appear
                        new AlertDialog.Builder(ScheduleActivity.this)
                                .setTitle(title)
                                .setMessage("This event starts at " + time + ".\n" + ext_desc)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })
                                .show();
                    }
                });
                // add the new relativelayout inside the main one
                rl.addView(newrl, params);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            serialKiller = JSONHandler.getInstance();
            // serialKiller.updateJSON();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("ScheduleActivity","onResume is super pissed rn");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent myIntent;
            myIntent = new Intent(ScheduleActivity.this, MainActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            ScheduleActivity.this.startActivity(myIntent);
            startActivityForResult(myIntent, 0);
            finish();
            overridePendingTransition(0,0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            try {
                serialKiller.getInstance();
                serialKiller.updateJSON();
                finish();
                overridePendingTransition(0,0);
                startActivity(getIntent());
            } catch (Exception e) {
                Toast.makeText(ScheduleActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean shouldLaunch = false;
        Intent myIntent;
        if (id == R.id.nav_map) {
            // IST Map
            shouldLaunch = true;
            myIntent = new Intent(ScheduleActivity.this, MainActivity.class);
        } else if (id == R.id.nav_announcements) {
            // Announcements
            shouldLaunch = true;
            myIntent = new Intent(ScheduleActivity.this, AnnouncementsActivity.class);
        } else if (id == R.id.nav_schedule) {
            // Schedule
            shouldLaunch = false;
            myIntent = new Intent(ScheduleActivity.this, ScheduleActivity.class);
        } else //if (id == R.id.nav_sponsors)
        {
            // Sponsors
            shouldLaunch = true;
            myIntent = new Intent(ScheduleActivity.this, SponsorsActivity.class);
        }
        if (shouldLaunch) {
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            ScheduleActivity.this.startActivity(myIntent);
            startActivityForResult(myIntent, 0);
            finish();
            overridePendingTransition(0,0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    // this is here for backwards-compatibility reasons. do not remove plz, thx.
    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
}
