package com.example.franky.projectclound;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    String []title;
    String []time ;
    Bitmap []image;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayList detail = new ArrayList();
    ArrayList date = new ArrayList();
    ArrayList<Bitmap> pic = new ArrayList<>();
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Shortperiod().execute();
        list = (ListView)findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                // TODO Auto-generated method stub
            //    final String item = (String) parent.getItemAtPosition(position);
              //  Toast.makeText(getApplicationContext(), String.valueOf(detail.get(position)), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Showdetail.class);
                intent.putExtra("datail",String.valueOf(detail.get(position)));
                startActivity(intent);
            }
        });

    }



    private class Shortperiod extends AsyncTask<String, Integer, String> {
        ProgressDialog pd;
        @Override
        protected void onPreExecute()
        {
            pd = new ProgressDialog(MainActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("Loading...");
            pd.setMessage("Loading images...");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setMax(100);
            pd.setProgress(0);
            pd.show();
        }

        protected String doInBackground(String... urls) {


            String getdata = "";
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url("http://cloudapis.azurewebsites.net/test1.php").build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    getdata = response.body().string();
                    try {
                        JSONArray jo1 = new JSONArray(getdata);
                        for (int i = 0;i<jo1.length();i++ ) {
                            JSONObject arr = jo1.getJSONObject(i);
                            titles.add(arr.getString("title"));
                            detail.add(arr.getString("detail"));
                            date.add(arr.getString("datet"));
                            byte[] decodedByte = Base64.decode(arr.getString("pic"), 0);
                            pic.add(BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length));
                            int c = (int)((100f / jo1.length()) * (i + 1));
                            publishProgress(c);
                        }

                    } catch (JSONException e) {

                    }
                    return getdata;
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        protected void onPostExecute(String jsonString) {
            title = new String[titles.size()];
            time = new String[date.size()];
            image = new Bitmap[pic.size()];
            for(int i=0;i<title.length;i++){
                title[i] = titles.get(i).toString();
                time[i] = date.get(i).toString();
                image[i] = pic.get(i);
            }
            AdapterHome adapter=new AdapterHome(MainActivity.this, title,time, image);
            list.setAdapter(adapter);
            pd.dismiss();
        }

        }

    }

