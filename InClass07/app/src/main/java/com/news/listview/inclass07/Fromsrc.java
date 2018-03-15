package com.news.listview.inclass07;

//Create Fromsrc Class
public class Fromsrc {
    String id, name;

    public Fromsrc() {
    }

    public Fromsrc(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fromsrc{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
