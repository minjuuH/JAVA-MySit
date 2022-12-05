package com.example.mysitreview;

public class Board {

    public String title;
    public String content;
    public String uid;
    public String date;
    public String order_date;
    public String intro;
    public String min_Popul;
    public String max_popul;
    public String stime;
    public String etime;
    public int pic;
    public String name;

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


    public String getMin_Popul() {
        return min_Popul;
    }

    public void setMin_Popul(String min_Popul) {this.min_Popul = min_Popul;}


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



}

