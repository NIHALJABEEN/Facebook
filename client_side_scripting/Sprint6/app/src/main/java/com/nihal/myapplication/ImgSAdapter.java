package com.nihal.myapplication;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nihal on 31-01-2017.
 */

public class ImgSAdapter extends ArrayAdapter<String>
{

    Context c;
    ArrayList<String> url;

    public ImgSAdapter(Context context, ArrayList<String> resource)

    {
        super(context,R.layout.imageloading,R.id.lyimgsts,resource);
        this.c=context;
        this.url=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater lv=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=lv.inflate(R.layout.imageloading,parent,false);


        ImageView img=(ImageView)row.findViewById(R.id.imgvsts);
        Picasso.with(getContext()).load(url.get(position)).resize(500,500).into(img);
        return row;
    }
}
