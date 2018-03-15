package com.news.listview.inclass07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements TaskUrl.GetNews{

    static String ARTICLE = "article";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        if(getIntent().getExtras()!=null){
            if(getIntent().getExtras().containsKey(MainActivity.CATEGORY)){
                String category = getIntent().getExtras().get(MainActivity.CATEGORY).toString();
                setTitle(category);
                new TaskUrl(NewsActivity.this).execute("https://newsapi.org/v2/top-headlines?country=us&apiKey=107e740a4dbd4de28d2ed3dfc694230e&category="
                        +category);
            }
        }
    }

    @Override
    public void handleNewsLoading(final ArrayList<NewsArticle> articles) {
        ListView listView = findViewById(R.id.listViewArticles);
        final AdapterNews articleAdapter = new AdapterNews(this, R.layout.article_list,articles);
        listView.setAdapter(articleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NewsActivity.this, BriefNews.class);
                intent.putExtra(ARTICLE, articles.get(position));
                startActivity(intent);
            }
        });
    }
}
