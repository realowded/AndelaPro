package com.example.uncommon.andelapro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by uncommon on 9/4/17.
 */

public class MyAdapter extends ArrayAdapter<Javalagos> {
    public MyAdapter(Context context, List<Javalagos> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Javalagos javalagos = getItem(position);

        View namesview = convertView;

        if (namesview == null){
            namesview = LayoutInflater.from(getContext()).inflate(R.layout.java_lag, parent, false);
        }

        ImageView imageView = namesview.findViewById(R.id.profile_pic);
        Picasso.with(getContext()).load(javalagos.getmAvater()).transform(new RoundedTransformation(100, 50)).fit().centerCrop().into(imageView);



        TextView myNames = namesview.findViewById(R.id.first_name);
        myNames.setText(javalagos.getmNames());

        return namesview;

        //return super.getView(position, convertView, parent);
    }
}
