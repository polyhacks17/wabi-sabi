package com.polyhacks.kintsugi;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

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

        float density = getResources().getDisplayMetrics().density;
        int densityDpi = getResources().getDisplayMetrics().densityDpi;

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.content_sponsors);
        ArrayList sponsorItemID = new ArrayList();

        final AlertDialog.Builder nosite = new AlertDialog.Builder(SponsorsActivity.this)
                .setTitle("No site available")
                .setMessage("There is no website available for this sponsor.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                });
        // List our sponsors
        ArrayList sponsors = new ArrayList();
        // sponsors are in the format: sponsor name, logo for big screens, logo for small screens, sponsor url
        sponsors.add(0, new Sponsor(this, "Florida Poly SGA", R.drawable.fpu_sga_upscaled, R.drawable.fpu_sga, "https://floridapolytechnic.org/get-involved/student-government-association/"));
        sponsors.add(1, new Sponsor(this, "Cole Engineering Services", R.drawable.cesi, R.drawable.cesi, "http://www.coleengineering.com/"));
        sponsors.add(2, new Sponsor(this, "Catapult", R.drawable.catapult_logo, R.drawable.catapult_logo_padded, "http://catapultlakeland.com//"));
        sponsors.add(3, new Sponsor(this, "Twilio", R.drawable.twilio_logo, R.drawable.twilio_logo_padded, "https://twilio.com/"));
        sponsors.add(4, new Sponsor(this, "MLH", R.drawable.mlh, R.drawable.mlh, "https://mlh.io/"));
        sponsors.add(5, new Sponsor(this, "Wolfram", R.drawable.wolfram_logo, R.drawable.wolfram_logo_padded, "https://wolfram.com/"));
        sponsors.add(6, new Sponsor(this, "GitHub", R.drawable.github_logo, R.drawable.github_logo_padded, "https://github.com/"));
        sponsors.add(7, new Sponsor(this, "Scott and Kim Johnson", R.drawable.skjohnson, R.drawable.skjohnson_padded, "nosite"));

        for (int index = 0; index < sponsors.size(); index++)
        {
            String name = ((Sponsor)sponsors.get(index)).getSponsorName();
            int logo = ((Sponsor)sponsors.get(index)).getLogoResource();
            int halfLogo = ((Sponsor)sponsors.get(index)).getHalfedLogoResource();
            final String site = ((Sponsor)sponsors.get(index)).getSite();
            Boolean needsPadding = (density <= 2.0);

            RelativeLayout newrl = new RelativeLayout(SponsorsActivity.this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            if (sponsorItemID.size() > 0 ) // if this is not the first item in the array
            {
                params.addRule(RelativeLayout.BELOW, (int) sponsorItemID.get(index - 1));
                if (!needsPadding && site != "nosite") {
                    newrl.setPadding(30, 120, 30, 5);
                } else {
                    newrl.setPadding(30, 5, 30, 30);
                }
            } else {
                newrl.setPadding(30, 5, 30, 30);
            }
            newrl.setId(generateViewId());
            sponsorItemID.add(newrl.getId());

            // add sponsor logo imageview into the relativelayout
            ImageView logoView = new ImageView(SponsorsActivity.this);
            RelativeLayout.LayoutParams logoParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            logoView.setId(generateViewId());
            // this is a terrible hack, but it works, so plz don't complain. also, if you touch this code, I will personally hurt you.
            if (needsPadding == false) {
                logoView.setImageBitmap(decodeSampledBitmapFromResource(getResources(), logo, (int) density * 125, (int) density * 125));
            } else {
                logoView.setImageBitmap(decodeSampledBitmapFromResource(getResources(), halfLogo, 150, 150));

            }
            logoView.setAdjustViewBounds(true);
            logoView.setPadding(30, 30, 30, 5);
            logoView.setScaleType(ImageView.ScaleType.FIT_XY);
            logoParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
            newrl.addView(logoView, logoParams);
            
            // add sponsor name textview into the relativelayout
            TextView sponsorNameTv = new TextView(SponsorsActivity.this);
            RelativeLayout.LayoutParams sponsorNameParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            sponsorNameTv.setId(generateViewId());
            SpannableString spanString = new SpannableString(name);
            spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
            sponsorNameTv.setText(spanString);
            sponsorNameTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
            sponsorNameTv.setPadding(15, 15, 15, 15);
            sponsorNameTv.setGravity(Gravity.CENTER_HORIZONTAL);
            sponsorNameParams.addRule(RelativeLayout.BELOW, logoView.getId());
            sponsorNameParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
            newrl.addView(sponsorNameTv, sponsorNameParams);

            newrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (site != "nosite") {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(site));
                        startActivity(i);
                    } else {
                        nosite.show();
                    }
                }
            });

            rl.addView(newrl, params);
        }
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
            myIntent = new Intent(SponsorsActivity.this, AnnouncementsActivity.class);
        } else if (id == R.id.nav_schedule) {
            // Schedule
            shouldLaunch = true;
            myIntent = new Intent(SponsorsActivity.this, ScheduleActivity.class);
        } else if (id == R.id.nav_sponsors)
        {
            // Sponsors
            shouldLaunch = false;
            myIntent = null;
        } else //if (id == R.id.nav_emergency)
        {
            // Emergency Contact
            shouldLaunch = true;
            myIntent = new Intent(SponsorsActivity.this, EmergencyContactActivity.class);
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

    private static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                          int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds = true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {

        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
