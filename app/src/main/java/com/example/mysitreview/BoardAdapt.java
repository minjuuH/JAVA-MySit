package com.example.mysitreview;

public class BoardAdapt {
    private String title;
    private String intro;
    private String stime;
    private String etime;
    private String now_popul;
    private String max_popul;
    private String introduce;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    //한줄 소개 부분 추가
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getMax_popul() {
        return max_popul;
    }
    public void setMax_popul(String max_popul) {
        this.max_popul = max_popul;
    }

    //시작일 추가
    public String getStime() {return stime;}
    public void setStime(String stime) {this.stime = stime;}

    public String getEtime() {
        return etime;
    }
    public void setEtime(String etime) {this.etime = etime;}

    public String getNow_popul(){return now_popul;}
    public void setNow_popul(String now_popul){ this.now_popul = now_popul;}

    public String getIntroduce() {
        return introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

}
