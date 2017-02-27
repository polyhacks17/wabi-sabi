package com.polyhacks.kintsugi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class SponsorsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsors);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_sponsors);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent myIntent;
            myIntent = new Intent(SponsorsActivity.this, MainActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            SponsorsActivity.this.startActivity(myIntent);
            startActivityForResult(myIntent, 0);
            finish();
            overridePendingTransition(0,0);
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
            Intent myIntent = new Intent(SponsorsActivity.this, InfoActivity.class);
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
            shouldLaunch = true;
            myIntent = new Intent(SponsorsActivity.this, MainActivity.class);
        } else if (id == R.id.nav_announcements) {
            // Announcements
            shouldLaunch = true;
            myIntent = new Intent(SponsorsActivity.this, AnnouncementsActivity.class); // CHANGEME
        } else if (id == R.id.nav_schedule) {
            // Schedule
            shouldLaunch = true;
            myIntent = new Intent(SponsorsActivity.this, ScheduleActivity.class);
        } else //if (id == R.id.nav_sponsors)
        {
            // Sponsors
            shouldLaunch = false;
            myIntent = null;
        }
        if (shouldLaunch) {
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            SponsorsActivity.this.startActivity(myIntent);
            startActivityForResult(myIntent, 0);
            finish();
            overridePendingTransition(0,0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
