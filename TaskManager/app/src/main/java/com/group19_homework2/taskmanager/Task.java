package com.group19_homework2.taskmanager;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Serializable {

    String title;
    String date;
    String time;
    String priority;
    String dateF;
    String timeF;
    Date date_time;
    int id;

    public Task(String title, String date, String time, String priority,Date date_time,String dateF,String timeF,int d) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.priority = priority;
        this.date_time =date_time;
        this.dateF=dateF;
        this.timeF=timeF;
        this.id=id;
    }

    public Date getdate(){

        return this.date_time;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", priority='" + priority + '\'' +
                '}';
    }
}

