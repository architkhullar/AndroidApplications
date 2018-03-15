package com.group19.inclass03;

import java.io.Serializable;

class StudentClass implements Serializable {

    String name;
    String email;
    String dept;
    int mood;

    public StudentClass(String name, String email, String dept, int mood) {
        this.name = name;
        this.email = email;
        this.dept = dept;
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dept='" + dept + '\'' +
                ", mood=" + mood +
                '}';
    }
}
