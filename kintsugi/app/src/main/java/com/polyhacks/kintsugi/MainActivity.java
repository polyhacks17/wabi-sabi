package com.polyhacks.kintsugi;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Boolean backPressedOnce = false;
    Boolean mapIsOnBottomFloor = true;
    PhotoViewAttacher mapAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_map);
        navigationView.setNavigationItemSelectedListener(this);

        RelativeLayout mapContainer = (RelativeLayout) findViewById(R.id.content_main);
        mapContainer.setBackgroundResource(R.drawable.ist_map_bg_gradient);
        final PhotoView mapView = (PhotoView) findViewById(R.id.mapView);
        final Drawable mapBottomFloor = getResources().getDrawable(R.drawable.ist_map_bottomfloor_walpha);
        final Drawable mapTopFloor = getResources().getDrawable(R.drawable.event_floor);

        mapView.setImageDrawable(mapBottomFloor);
        mapView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mapAttacher = new PhotoViewAttacher(mapView);

        final FloatingActionButton mapFab = (FloatingActionButton)findViewById(R.id.mapFAB);
        mapFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapIsOnBottomFloor == true)
                {
                    mapView.setImageDrawable(mapTopFloor);
                    mapIsOnBottomFloor = false;
                    mapAttacher.update();
                    mapFab.setImageResource(R.drawable.ic_arrow_down);
                } else {
                    mapView.setImageDrawable(mapBottomFloor);
                    mapIsOnBottomFloor = true;
                    mapAttacher.update();
                    mapFab.setImageResource(R.drawable.ic_arrow_up);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (backPressedOnce == false) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                backPressedOnce = true;
                Toast.makeText(MainActivity.this, "Press back again to exit.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // exit app
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_info) {
            Intent myIntent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(myIntent);
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
            shouldLaunch = false;
            myIntent = null; // We are here already
        } else if (id == R.id.nav_announcements) {
            // Announcements
            shouldLaunch = true;
            myIntent = new Intent(MainActivity.this, AnnouncementsActivity.class);
        } else if (id == R.id.nav_schedule) {
            // Schedule
            shouldLaunch = true;
            myIntent = new Intent(MainActivity.this, ScheduleActivity.class);
        } else if (id == R.id.nav_sponsors)
        {
            // Sponsors
            shouldLaunch = true;
            myIntent = new Intent(MainActivity.this, SponsorsActivity.class);
        } else //if (id == R.id.nav_emergency)
        {
            // Emergency Contact
            shouldLaunch = true;
            myIntent = new Intent(MainActivity.this, EmergencyContactActivity.class);
        }
        if (shouldLaunch) {
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            MainActivity.this.startActivity(myIntent);
            startActivityForResult(myIntent, 0);
            finish();
            overridePendingTransition(0,0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
