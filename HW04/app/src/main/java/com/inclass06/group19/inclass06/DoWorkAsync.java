package com.inclass06.group19.inclass06;

import android.content.Context;
import android.location.Address;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by archi on 2/12/2018.
 */

public class DoWorkAsync extends AsyncTask<String, Integer, ArrayList<News>>{

    URL url;
    Inter activity;
    ProgressBar progressBar;
    TextView txt;
    //ImageView imageView;
    //Bitmap bitmap = null;





    public DoWorkAsync(URL url, ProgressBar progressBar,TextView txt,Inter i) {

        this.url = url;
        this.activity = i;
        this.progressBar = progressBar;
        this.txt=txt;
    }
    /*public DoWorkAsync(URL url, ImageView iv, Inter i) {

        this.imageView = iv;
        this.url = url;
        this.activity = i;
    }*/



    public interface Inter{
        void getProperty(ArrayList<News> s);
        //void getProperty1(String data1) throws MalformedURLException;


    }

    @Override
    protected ArrayList<News> doInBackground(String... strings) {

        ArrayList<News> result = new ArrayList<News>();
        HttpURLConnection connection = null;

        Log.d("url",url+"");
        try {
            Log.d("url", url + "");
            URL url = this.url;
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            Log.d("url",connection.getResponseCode()+"");
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Log.d("url", url + "here");
                result = NewsParser.NewsPullParser.parseNews(connection.getInputStream());
            }
        }catch (Exception e) {
            Log.d("Demo:", e + "");

        }


        return result;
    }


    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
        txt.setVisibility(View.VISIBLE);
        super.onPreExecute();


    }

    @Override
    protected void onPostExecute(ArrayList<News> s) {
        progressBar.setVisibility(View.GONE);
        txt.setVisibility(View.GONE);
        super.onPostExecute(s);
        activity.getProperty(s);



    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);


    }

}


