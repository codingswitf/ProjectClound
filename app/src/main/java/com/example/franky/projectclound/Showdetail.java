package com.example.franky.projectclound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Franky on 18/5/2559.
 */
public class Showdetail extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdetail);
        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("datail");
        Log.i("textinshowdetail", text);
        TextView texts = (TextView) findViewById(R.id.datail);
        texts.setText(Html.fromHtml(text));
    }

}
