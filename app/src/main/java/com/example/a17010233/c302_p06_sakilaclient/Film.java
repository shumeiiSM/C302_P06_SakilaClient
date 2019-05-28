package com.example.a17010233.c302_p06_sakilaclient;

public class Film {
    private int id;
    private String title;
    private String year;
    private String rate;

    public Film(int id, String title, String year, String rate) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
