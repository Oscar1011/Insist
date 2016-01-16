package com.android.shengbx.insist.sql;

/**
 * Created by shengbx on 1/8/16.
 */
public class InsistInfo {
    private long id;
    private String title;
    private int type = 0;
    private int dayLongest = 0;
    private int dayNow = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDayLongest() {
        return dayLongest;
    }

    public void setDayLongest(int dayLongest) {
        this.dayLongest = dayLongest;
    }

    public int getDayNow() {
        return dayNow;
    }

    public void setDayNow(int dayNow) {
        this.dayNow = dayNow;
    }
}
