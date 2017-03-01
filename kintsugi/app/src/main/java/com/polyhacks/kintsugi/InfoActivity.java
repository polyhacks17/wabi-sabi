package com.polyhacks.kintsugi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.polyhacks.kintsugi.SponsorsActivity.generateViewId;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.content_info);

        TextView titleTeam = new TextView(InfoActivity.this);
        titleTeam.setId(generateViewId());
        SpannableString spannableTeam = new SpannableString("Dev Team");
        spannableTeam.setSpan(new StyleSpan(Typeface.BOLD), 0, spannableTeam.length(), 0);
        titleTeam.setText(spannableTeam);
        titleTeam.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
        rl.addView(titleTeam);

        TextView gregText = new TextView(InfoActivity.this);
        RelativeLayout.LayoutParams gregTextParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        gregText.setId(generateViewId());
        gregText.setText("Greg Willard (r3pwn)");
        gregTextParams.addRule(RelativeLayout.BELOW, titleTeam.getId());
        gregText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        rl.addView(gregText, gregTextParams);

        TextView gregGHText = new TextView(InfoActivity.this);
        RelativeLayout.LayoutParams gregGHTextParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        gregGHText.setId(generateViewId());
        gregGHText.setText(" - Github");
        gregGHText.setTextColor(Color.BLUE);
        gregGHTextParams.addRule(RelativeLayout.BELOW, gregText.getId());
        gregGHText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        rl.addView(gregGHText, gregGHTextParams);
        gregGHText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/r3pwn/"));
                startActivity(i);
            }
        });

        TextView gregTwText = new TextView(InfoActivity.this);
        RelativeLayout.LayoutParams gregTwTextParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        gregTwText.setId(generateViewId());
        gregTwText.setText(" - Twitter");
        gregTwText.setTextColor(Color.BLUE);
        gregTwTextParams.addRule(RelativeLayout.BELOW, gregGHText.getId());
        gregTwText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        rl.addView(gregTwText, gregTwTextParams);
        gregTwText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://twitter.com/r3pwn/"));
                startActivity(i);
            }
        });

        TextView calebText = new TextView(InfoActivity.this);
        RelativeLayout.LayoutParams calebTextParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        calebText.setId(generateViewId());
        calebText.setText("Caleb Long (cerras0981)");
        calebTextParams.addRule(RelativeLayout.BELOW, gregTwText.getId());
        calebText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        calebText.setPadding(0, 10, 0, 0);
        rl.addView(calebText, calebTextParams);

        TextView calebGHText = new TextView(InfoActivity.this);
        RelativeLayout.LayoutParams calebGHTextParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        calebGHText.setId(generateViewId());
        calebGHText.setText(" - Github");
        calebGHText.setTextColor(Color.BLUE);
        calebGHTextParams.addRule(RelativeLayout.BELOW, calebText.getId());
        calebGHText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        calebGHText.setPadding(0, 0, 0, 20);
        rl.addView(calebGHText, calebGHTextParams);
        calebGHText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/qwerty0981/"));
                startActivity(i);
            }
        });

        View horizontalLine = new View(InfoActivity.this);
        RelativeLayout.LayoutParams horizLineParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        horizLineParams.addRule(RelativeLayout.BELOW, calebGHText.getId());
        horizontalLine.setId(generateViewId());
        horizontalLine.setBackgroundColor(Color.GRAY);
        rl.addView(horizontalLine, horizLineParams);

        TextView licensesTitle = new TextView(InfoActivity.this);
        RelativeLayout.LayoutParams licensesTitleParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        licensesTitle.setId(generateViewId());
        SpannableString spannableLicensesTitle = new SpannableString("Licenses");
        spannableLicensesTitle.setSpan(new StyleSpan(Typeface.BOLD), 0, spannableLicensesTitle.length(), 0);
        licensesTitle.setText(spannableLicensesTitle);
        licensesTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
        licensesTitleParams.addRule(RelativeLayout.BELOW, horizontalLine.getId());
        rl.addView(licensesTitle, licensesTitleParams);

        TextView licenseInfo = new TextView(InfoActivity.this);
        RelativeLayout.LayoutParams licenseInfoParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        licenseInfo.setId(generateViewId());
        licenseInfo.setText("This app uses the GSON library by Google, which is licensed " +
                "under the Apache 2 license. You can find a copy of the license below.");
        licenseInfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        licenseInfoParams.addRule(RelativeLayout.BELOW, licensesTitle.getId());
        licenseInfo.setPadding(0, 10, 0, 0);
        rl.addView(licenseInfo, licenseInfoParams);

        TextView license = new TextView(InfoActivity.this);
        RelativeLayout.LayoutParams licenseParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        license.setId(generateViewId());
        SpannableString spannableLicense = new SpannableString(
                "Copyright 2008 Google Inc.\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\"); " +
                "you may not use this file except in compliance with the License. " +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software " +
                "distributed under the License is distributed on an \"AS IS\" BASIS, " +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. " +
                "See the License for the specific language governing permissions and " +
                "limitations under the License.");
        license.setText(spannableLicense);
        license.setTypeface(Typeface.MONOSPACE);
        license.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        licenseParams.addRule(RelativeLayout.BELOW, licenseInfo.getId());
        license.setPadding(0, 20, 0, 0);
        rl.addView(license, licenseParams);
        /*  */
    }
}
