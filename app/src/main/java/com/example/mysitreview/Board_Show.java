package com.example.mysitreview;

public class Board_Show {
    private String title;
    private String content;
    private String max_popul;
    private String stime;
    private String etime;
    private String sdate;
    private String now_popul;
    private String date;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getMax_popul() {
        return max_popul;
    }

    public void setMax_popul(String max_popul) {
        this.max_popul = max_popul;
    }


    public String getStime() {return stime;}

    public void setStime(String stime) {this.stime = stime;}


    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {this.etime = etime;}


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {this.sdate = sdate;}


    public String getNow_popul(){return now_popul;}

    public void setNow_popul(String now_popul){ this.now_popul = now_popul;}


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
