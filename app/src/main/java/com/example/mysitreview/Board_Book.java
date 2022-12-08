package com.example.mysitreview;

public class Board_Book {
    private String  title;
    private String  content;
    private String  name;
    private String  phNum;
    private String  rtime;      //예약확정일자 및 시간
    private String  dtime;      //이벤트 시작시간
    private String  date;       //이벤트 시작일
    private String  uid;
    private String  type;
    private String  etime;      //모집 마감일



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhNum() {
        return phNum;
    }

    public void setPhNum(String phNum) {
        this.phNum = phNum;
    }


    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }


    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getType(){return type;}

    public void setType(String type){ this.type = type;}


    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {this.etime = etime;}

}