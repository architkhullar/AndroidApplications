package com.hw03.group19.hw03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by archi on 2/16/2018.
 */

public class ImageDownloadAsyncTask extends AsyncTask<String,Integer,String> {

    URL url;
    ImageView imageView;
    //Inter activity;
    Bitmap bitmap;

    public ImageDownloadAsyncTask(URL url, ImageView imageView) {
        this.url = url;
        //this.activity = inter;
        this.imageView = imageView;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (bitmap != null && imageView != null) {
            imageView.setImageBitmap(bitmap);
            Log.d("demo", "here");
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {
            String result = null;
            HttpURLConnection connection = null;
            bitmap = null;
            try {
                URL url = this.url;
                Log.d("Demo",url+"");
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                }
                result = "ok";

            } catch (Exception e) {
                Log.d("Demo:", e + "");
            }
            return result;
    }

}



