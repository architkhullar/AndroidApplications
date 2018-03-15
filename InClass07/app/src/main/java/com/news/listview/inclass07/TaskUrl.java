package com.news.listview.inclass07;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



public class TaskUrl extends AsyncTask<String, Integer, ArrayList<NewsArticle>> {

    GetNews news;
    ProgressDialog progressDialog;

    public TaskUrl(GetNews news) {
        this.news = news;
    }

    @Override
    protected ArrayList<NewsArticle> doInBackground(String... strings) {
        {
            StringBuilder sb = new StringBuilder();
            HttpURLConnection con = null;
            InputStream is = null;
            ArrayList<NewsArticle> result = new ArrayList<>();
            try{
                URL url = new URL(strings[0]);
                con = (HttpURLConnection) url.openConnection();
                is = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = "";
                while((line = reader.readLine())!=null){
                    sb.append(line);
                }
                String json = sb.toString();
                JSONObject root = new JSONObject(json);
                JSONArray articles = root.getJSONArray("articles");
                for(int i=0;i<articles.length();i++){
                    JSONObject article = articles.getJSONObject(i);
                    JSONObject source = article.getJSONObject("source");
                    NewsArticle a = new NewsArticle();
                    Fromsrc s = new Fromsrc();
                    a.title = article.getString("title");
                    a.publishedAt = article.getString("publishedAt");
                    a.urlToImage = article.getString("urlToImage");
                    a.description = article.getString("description");
                    Log.d("article", a.toString());
                    result.add(a);
                }

            }catch(Exception e){
                Log.d("Exception", e.getMessage());
                e.printStackTrace();
            }finally {
                if(con!=null){
                    con.disconnect();
                }if(is!=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
        }
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog((Context) news);
        progressDialog.setMessage("Loading News ... ");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<NewsArticle> articles) {
        news.handleNewsLoading(articles);
        progressDialog.dismiss();
    }

    public static interface GetNews{
        public void handleNewsLoading(ArrayList<NewsArticle> articles);
    }
}
