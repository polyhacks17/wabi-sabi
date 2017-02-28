package com.polyhacks.kintsugi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.StyleSpan;
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

import static android.view.View.GONE;

public class AnnouncementsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    JSONHandler serialKiller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcements);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // drawer.openDrawer(GravityCompat.START, false); // start with the drawer open

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_announcements);
        navigationView.setNavigationItemSelectedListener(this);

        // The "Loading..." TextView here has an id of R.id.announcementsloadingtext
        // Also, the RelativeLayout we need to modify has an id of R.id.content_announcements

        try {
            serialKiller = JSONHandler.getInstance();
        } catch (Exception e) {
            //there is problem, plz fix
        }

        TextView tv = (TextView) findViewById(R.id.announcementsloadingtext);
        tv.setVisibility(GONE);

        /*
            This code is special. Please don't touch. It will bite back, it is a flesh-eating demon straight from hell.
            DO NOT FEED AFTER MIDNIGHT.
         */
        int PREFERRED_TEXT_SIZE = 17; // in DiP
        float PREFERRED_PADDING_SIZE = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        boolean hasInternet = true;

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.content_announcements);
        ArrayList announcementItemID = new ArrayList();
        ArrayList announcements = serialKiller.getAnnouncements();
        Log.d("KINTSUGI", serialKiller.getTitle());

        if (serialKiller.getTitle().equalsIgnoreCase("It hasn't loaded the object"))
        {
            tv.setText("No internet access detected.");
            tv.setVisibility(View.VISIBLE);
            hasInternet = false;
        }

        if (hasInternet)
        {
            for(int i = 0; i < announcements.size(); i++) {
                HashMap item = (HashMap) announcements.get(i);
                String title = (String) item.get("title");
                String desc = (String) item.get("desc");

                // declare and define the new relativelayout in which the textviews will reside
                RelativeLayout newrl = new RelativeLayout(AnnouncementsActivity.this);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                if (announcementItemID.size() > 0 ) // if this is not the first item in the array
                {
                    params.addRule(RelativeLayout.BELOW, (int) announcementItemID.get(i - 1));
                }
                newrl.setId(generateViewId());
                announcementItemID.add(newrl.getId());

                // add title textview into the relativelayout
                TextView titleTv = new TextView(AnnouncementsActivity.this);
                RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                titleTv.setId(generateViewId());
                SpannableString spanString = new SpannableString(title);
                spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
                titleTv.setText(spanString);
                titleTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, PREFERRED_TEXT_SIZE + 2);
                titleTv.setPadding((int)PREFERRED_PADDING_SIZE, (int)PREFERRED_PADDING_SIZE, (int)PREFERRED_PADDING_SIZE, 0);
                newrl.addView(titleTv, titleParams);

                // add desc textview into the relativelayout
                TextView descTv = new TextView(AnnouncementsActivity.this);
                RelativeLayout.LayoutParams descParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                descParams.addRule(RelativeLayout.BELOW, titleTv.getId());
                descTv.setId(generateViewId());
                descTv.setText(desc);
                descTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, PREFERRED_TEXT_SIZE);
                descTv.setPadding((int)PREFERRED_PADDING_SIZE, 0, (int)PREFERRED_PADDING_SIZE, (int)PREFERRED_PADDING_SIZE);
                newrl.addView(descTv, descParams);

                // horizontal line
                View horizontalLine = new View(AnnouncementsActivity.this);
                RelativeLayout.LayoutParams horizLineParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, 1);
                horizLineParams.addRule(RelativeLayout.BELOW, descTv.getId());
                horizontalLine.setBackgroundColor(Color.GRAY);
                newrl.addView(horizontalLine, horizLineParams);

                // add the new relativelayout inside the main one
                rl.addView(newrl, params);
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent myIntent;
            myIntent = new Intent(AnnouncementsActivity.this, MainActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            AnnouncementsActivity.this.startActivity(myIntent);
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

        if (id == R.id.action_refresh) {
            try {
                serialKiller.getInstance();
                serialKiller.updateJSON();
                finish();
                overridePendingTransition(0,0);
                startActivity(getIntent());
            } catch (Exception e) {
                Toast.makeText(AnnouncementsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
            myIntent = new Intent(AnnouncementsActivity.this, MainActivity.class);
        } else if (id == R.id.nav_announcements) {
            // Announcements
            shouldLaunch = false;
            myIntent = new Intent(AnnouncementsActivity.this, AnnouncementsActivity.class);
        } else if (id == R.id.nav_schedule) {
            // Schedule
            shouldLaunch = true;
            myIntent = new Intent(AnnouncementsActivity.this, ScheduleActivity.class);
        } else if (id == R.id.nav_sponsors)
        {
            // Sponsors
            shouldLaunch = true;
            myIntent = new Intent(AnnouncementsActivity.this, SponsorsActivity.class);
        } else //if (id == R.id.nav_emergency)
        {
            // Emergency Contact
            shouldLaunch = true;
            myIntent = new Intent(AnnouncementsActivity.this, EmergencyContactActivity.class);
        }
        if (shouldLaunch) {
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            AnnouncementsActivity.this.startActivity(myIntent);
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
