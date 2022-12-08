package com.example.mysitreview;

public class Board {

    public String title;
    public String content;
    public String uid;
    public String date;
    public String order_date;
    public String intro;
    public String sdate;
    public String max_popul;
    public String stime;
    public String etime;
    public int pic;
    public String name;
    public String now_popul;
    private String type;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }


    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {this.sdate = sdate;}


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


    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }


    public int getPic() {return pic;}

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName(){return name;}

    public void setName(String name){ this.name = name;}

    public String getType(){return type;}

    public void setType(String type){ this.type = type;}


    public String getNow_popul(){return now_popul;}

    public void setNow_popul(String now_popul){ this.now_popul = now_popul;}


}
