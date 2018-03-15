package com.hw03.group19.hw03;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by archi on 2/16/2018.
 */

public class InitialLoadAsyncTask extends AsyncTask<String,Integer,String> {


    URL url;
    Inter activity;

    public interface Inter{
        void getInitialData(String data) throws MalformedURLException;

    }

    public InitialLoadAsyncTask(URL url, Inter activity) {
        this.url = url;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = null;
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = this.url;
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("|");
                }
                result = stringBuilder.toString();
            }
        } catch (Exception e) {
            Log.d("Demo:", e + "");

        }
        return result;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            activity.getInitialData(s);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
