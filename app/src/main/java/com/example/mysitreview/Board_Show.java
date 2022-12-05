package com.example.mysitreview;

public class Board_Show {
    private String title;
    private String content;
    private String min_popul;
    private String max_popul;
    private String stime;
    private String etime;


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


    //최소 인원 추가
    public String getMin_popul() {
        return max_popul;
    }

    public void setMin_popul(String max_popul) {
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



}
