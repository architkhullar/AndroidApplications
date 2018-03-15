package com.hw03.group19.hw03;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by archi on 2/16/2018.
 */

public class Question implements Serializable{
    String queNo;
    String que;
    String[] options;
    String link;
    String ans;

    public Question(String queNo, String que, String link,String[] options , String ans) {
        this.queNo = queNo;
        this.que = que;
        this.options = options;
        this.link = link;
        this.ans = ans;
    }
}
