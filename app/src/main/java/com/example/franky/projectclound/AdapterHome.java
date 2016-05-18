package com.example.franky.projectclound;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franky.projectclound.R;

/**
 * Created by Franky on 17/5/2559.
 */
public class AdapterHome extends ArrayAdapter<String> {

    private final Activity context;
    private String[] titles = new String[0];
    private String[] date = new String[0];
    private Bitmap[] imgid = new Bitmap[0];

    public AdapterHome(Activity context, String[] titles, String[] date,Bitmap[] imgid) {
        super(context, R.layout.addapterhome, titles);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.titles = titles;
        this.date = date;
        this.imgid = imgid;
    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.addapterhome, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.title);
        TextView txtTitles = (TextView) rowView.findViewById(R.id.time);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image);


        txtTitle.setText(titles[position]);
        txtTitles.setText(date[position]);
        imageView.setImageBitmap(imgid[position]);
        return rowView;

    }
}
