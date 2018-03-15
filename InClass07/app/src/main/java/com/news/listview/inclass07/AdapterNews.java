package com.news.listview.inclass07;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;



public class AdapterNews extends ArrayAdapter<NewsArticle> {

    public AdapterNews(@NonNull Context context, int resource, @NonNull List<NewsArticle> objects) {
        super(context, resource, objects);
    }
    private static class ViewHolder{
        ImageView imageView;
        TextView titletv, publishedtv;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NewsArticle article = getItem(position);
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_list, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.titletv = convertView.findViewById(R.id.titletv);
            viewHolder.publishedtv = convertView.findViewById(R.id.publishedtv);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Log.d("title", article.title);
        viewHolder.titletv.setText(article.title);
        viewHolder.publishedtv.setText(article.publishedAt);
        Picasso.with(getContext()).load(article.urlToImage).into(viewHolder.imageView);//Display Image of the article
        return convertView;
    }
}
