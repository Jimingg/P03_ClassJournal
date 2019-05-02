package com.example.a17010233.p03_classjournal;

import java.io.Serializable;

public class Details implements Serializable {

    private int week;
    private String grade;
    private String module;

    public Details(int week, String grade, String module) {
        this.week = week;
        this.grade = grade;
        this.module = module;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
