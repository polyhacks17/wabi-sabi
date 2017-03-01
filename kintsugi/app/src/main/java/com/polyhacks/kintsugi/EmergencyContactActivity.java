package com.polyhacks.kintsugi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EmergencyContactActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_emergency);
        navigationView.setNavigationItemSelectedListener(this);

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.content_emergency);

        TextView campusPoliceTitleTv = new TextView(EmergencyContactActivity.this);
        RelativeLayout.LayoutParams campusPoliceParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        campusPoliceTitleTv.setId(ScheduleActivity.generateViewId());
        SpannableString spanString = new SpannableString("Campus Police");
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
        campusPoliceTitleTv.setText(spanString);
        campusPoliceTitleTv.setPadding(15, 15, 15, 15);
        campusPoliceTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        rl.addView(campusPoliceTitleTv, campusPoliceParams);

        // horizontal line
        View horizontalLine = new View(EmergencyContactActivity.this);
        RelativeLayout.LayoutParams horizLineParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        horizLineParams.addRule(RelativeLayout.BELOW, campusPoliceTitleTv.getId());
        horizontalLine.setBackgroundColor(Color.GRAY);
        rl.addView(horizontalLine, horizLineParams);

        TextView campusPoliceNumberTv = new TextView(EmergencyContactActivity.this);
        RelativeLayout.LayoutParams campusPoliceNumberParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        campusPoliceNumberTv.setId(ScheduleActivity.generateViewId());
        campusPoliceNumberTv.setText("(863)-874-8472");
        campusPoliceNumberTv.setPadding(15, 15, 15, 0);
        campusPoliceNumberTv.setTextColor(Color.BLUE);
        campusPoliceNumberTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        campusPoliceNumberTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8638748472"));
                startActivity(intent);
            }
        });
        campusPoliceNumberParams.addRule(RelativeLayout.BELOW, campusPoliceTitleTv.getId());
        rl.addView(campusPoliceNumberTv, campusPoliceNumberParams);

        TextView campusPoliceDescTv = new TextView(EmergencyContactActivity.this);
        RelativeLayout.LayoutParams campusPoliceDescParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        campusPoliceDescTv.setId(ScheduleActivity.generateViewId());
        campusPoliceDescTv.setText("For non-emergencies contact the police department by tapping " +
                "the number above. If you are near a blue light emergency phone, simply press the" +
                " red button and you will be contacted by a police officer. For any emergency " +
                "situations, dial 9-1-1.");
        campusPoliceDescTv.setPadding(15, 15, 15, 15);
        campusPoliceDescTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        campusPoliceDescTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8638748472"));
                startActivity(intent);
            }
        });
        campusPoliceDescParams.addRule(RelativeLayout.BELOW, campusPoliceNumberTv.getId());
        rl.addView(campusPoliceDescTv, campusPoliceDescParams);

        TextView devTeamTitleTv = new TextView(EmergencyContactActivity.this);
        RelativeLayout.LayoutParams devTeamTitleParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        devTeamTitleTv.setId(ScheduleActivity.generateViewId());
        SpannableString devTeamSpanString = new SpannableString("PolyHacks Dev Team");
        devTeamSpanString.setSpan(new StyleSpan(Typeface.BOLD), 0, devTeamSpanString.length(), 0);
        devTeamTitleTv.setText(devTeamSpanString);
        devTeamTitleTv.setPadding(15, 60, 15, 15);
        devTeamTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        devTeamTitleParams.addRule(RelativeLayout.BELOW, campusPoliceDescTv.getId());
        rl.addView(devTeamTitleTv, devTeamTitleParams);

        // horizontal line
        View horizontalLineTwo = new View(EmergencyContactActivity.this);
        RelativeLayout.LayoutParams horizLineTwoParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        horizLineTwoParams.addRule(RelativeLayout.BELOW, devTeamTitleTv.getId());
        horizontalLineTwo.setBackgroundColor(Color.GRAY);
        rl.addView(horizontalLineTwo, horizLineTwoParams);

        TextView devTeamNumberTv = new TextView(EmergencyContactActivity.this);
        RelativeLayout.LayoutParams devTeamNumberParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        devTeamNumberTv.setId(ScheduleActivity.generateViewId());
        devTeamNumberTv.setText("(863)-606-8486");
        devTeamNumberTv.setPadding(15, 15, 15, 0);
        devTeamNumberTv.setTextColor(Color.BLUE);
        devTeamNumberTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        devTeamNumberTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:8636068486"));
                startActivity(sendIntent);
            }
        });
        devTeamNumberParams.addRule(RelativeLayout.BELOW, devTeamTitleTv.getId());
        rl.addView(devTeamNumberTv, devTeamNumberParams);

        TextView devTeamDescTv = new TextView(EmergencyContactActivity.this);
        RelativeLayout.LayoutParams devTeamDescParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        devTeamDescTv.setId(ScheduleActivity.generateViewId());
        devTeamDescTv.setText("Texting this number will put you in contact with one of the " +
                            "members of the Dev Team. You can contact us if you wish to speak " +
                            "with a mentor or wish to receive technical help related to the event.");
        devTeamDescTv.setPadding(15, 15, 15, 15);
        devTeamDescTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        devTeamDescTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:8636068486"));
                startActivity(sendIntent);
            }
        });
        devTeamDescParams.addRule(RelativeLayout.BELOW, devTeamNumberTv.getId());
        rl.addView(devTeamDescTv, devTeamDescParams);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent myIntent;
            myIntent = new Intent(EmergencyContactActivity.this, MainActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            EmergencyContactActivity.this.startActivity(myIntent);
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
            Intent myIntent = new Intent(EmergencyContactActivity.this, InfoActivity.class);
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
            myIntent = new Intent(EmergencyContactActivity.this, MainActivity.class);
        } else if (id == R.id.nav_announcements) {
            // Announcements
            shouldLaunch = true;
            myIntent = new Intent(EmergencyContactActivity.this, AnnouncementsActivity.class);
        } else if (id == R.id.nav_schedule) {
            // Schedule
            shouldLaunch = true;
            myIntent = new Intent(EmergencyContactActivity.this, ScheduleActivity.class);
        } else if (id == R.id.nav_sponsors)
        {
            // Sponsors
            shouldLaunch = true;
            myIntent = new Intent(EmergencyContactActivity.this, SponsorsActivity.class);
        } else //if (id == R.id.nav_emergency)
        {
            // Emergency Contact
            shouldLaunch = false;
            myIntent = new Intent(EmergencyContactActivity.this, EmergencyContactActivity.class);
        }
        if (shouldLaunch) {
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            EmergencyContactActivity.this.startActivity(myIntent);
            startActivityForResult(myIntent, 0);
            finish();
            overridePendingTransition(0,0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
