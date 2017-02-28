package com.polyhacks.kintsugi;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by r3pwn on 2/27/17.
 */

public class Sponsor {
    private Context context;
    private String name;
    private String logoFileName;
    private String site;
    private int logoResource;
    private int halfedLogoResource;

    Sponsor(Context context, String name, int drawableID, int halfedDrawableID, String site)
    {
        this.context = context;
        this.name = name;
        this.logoResource = drawableID;
        this.halfedLogoResource = halfedDrawableID;
        this.site = site;
    }

    public String getSponsorName()
    {
        return name;
    }

    public int getLogoResource()
    {
        return logoResource;
    }

    public int getHalfedLogoResource() {
        return halfedLogoResource;
    }

    public String getSite()
    {
        return site;
    }

    public Drawable getDrawable()
    {
        Drawable tempDrawable = null;
        AssetManager myAssets = context.getAssets();
        try {
            InputStream inputStream = myAssets.open(logoFileName);
            tempDrawable = Drawable.createFromStream(inputStream, null);
        } catch (IOException ioe)
        {
            Toast.makeText(context, "Something went wrong while trying to load the asset " + logoFileName + ".", Toast.LENGTH_SHORT).show();
        }
        return tempDrawable;
    }
}
