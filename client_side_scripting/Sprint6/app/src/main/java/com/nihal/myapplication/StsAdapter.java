package com.nihal.myapplication;

import android.content.Context;
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
 * Created by Nihal on 17-02-2017.
 */

public class StsAdapter extends ArrayAdapter<String>

{

    Context c;
    ArrayList<String>sts;


    public StsAdapter(Context context, ArrayList<String> resource)

    {
        super(context,R.layout.stst_to_lstv,R.id.RltvLytSts,resource);
        this.c=context;
        this.sts=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)

    {
        LayoutInflater lv=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=lv.inflate(R.layout.stst_to_lstv,parent,false);


        TextView st=(TextView) row.findViewById(R.id.TxtVSts);
        st.setText(sts.get(position));

        return row;
    }
}
