package com.inclass06.group19.inclass06;

/**
 * Created by archi on 2/19/2018.
 */

public class News {
    String title;
    String publishedat;
    String Desc;
    String url;

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", publishedat='" + publishedat + '\'' +
                ", Desc='" + Desc + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
