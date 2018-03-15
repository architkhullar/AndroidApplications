package com.news.listview.inclass07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BriefNews extends AppCompatActivity {

    TextView title, publishedAt, summary;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Detail Activity");
        setContentView(R.layout.activity_news_detail);
        if(getIntent().getExtras()!=null){

            //Get Intent from new activity article
            if(getIntent().getExtras().containsKey(NewsActivity.ARTICLE)){
                NewsArticle article = (NewsArticle) getIntent().getExtras().getSerializable(NewsActivity.ARTICLE);
                title = findViewById(R.id.detailTitleTv);
                publishedAt = findViewById(R.id.detailPublishedtv);
                summary = findViewById(R.id.detailSummarytv);
                imageView = findViewById(R.id.detailImageView);
                title.setText(article.title);
                publishedAt.setText(article.publishedAt);
                summary.setText(article.description);
                Picasso.with(this).load(article.urlToImage).into(imageView);


            }
        }
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
