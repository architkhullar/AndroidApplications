package com.inclass06.group19.inclass06;

import android.location.Address;
import android.util.Log;
import android.util.Xml;
import android.widget.Switch;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by archi on 2/22/2018.
 */

public class NewsParser {
    public static class NewsSAXParser extends DefaultHandler{}

    public static class NewsPullParser {
        static public ArrayList<News> parseNews(InputStream inputStream) throws XmlPullParserException, IOException {

            Log.d("obj","here");
            ArrayList<News> news = new ArrayList<News>();
            News news1 = null;
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, "UTF-8");
            //factory.setNamespaceAware(true);

            int event = parser.getEventType();
            int flag =0;
            //Log.d("obj",XmlPullParser.START_TAG+"");
            while (event!=XmlPullParser.END_DOCUMENT){
                //Log.d("obj",event+"");
                switch (event){

                    case XmlPullParser.START_TAG:
                        //Log.d("xyz",parser.getName()+"");
                        if (parser.getName().equals("item")) {
                            flag =1;
                            news1 = new News();
                            Log.d("obj","object created");
                        }else if(parser.getName().equals("title") && flag ==1){
                            news1.title = parser.nextText().trim();
                            Log.d("obj",news1.title);
                        }else if (parser.getName().equals("description")&& flag ==1){
                            String d = parser.nextText().trim();
                            news1.Desc=d.split("<div")[0];
                            //final Pattern pattern = Pattern.compile("(div)");
                            //final Matcher matcher = pattern.matcher(d);
                            //news1.Desc=matcher.group(1);
                            Log.d("obj",news1.Desc);
                        }else if (parser.getName().equals("pubDate")&& flag ==1){
                            news1.publishedat = parser.nextText().trim();
                            Log.d("obj",news1.publishedat);
                        }else if (parser.getName().equals("media:content")&& flag ==1){
                            flag =0;
                            news1.url = parser.getAttributeValue(null,"url");
                            Log.d("obj",news1.url);
                            news.add(news1);
                        }



                }
                event = parser.next();
            }
            return news;
        }
    }
        //Address address;


    }

