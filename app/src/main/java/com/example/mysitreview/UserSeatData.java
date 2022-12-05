package com.example.mysitreview;

public class UserSeatData {
    private String title;
    private String sdate;

    //생성자
    public UserSeatData(String title, String sdate) {
        this.title = title;
        this.sdate = sdate;
    }

    //Getter
    public String getTitle() {
        return title;
    }

    public String getSdate() {
        return sdate;
    }

    //Setter
    public void setTitle(String title) {
        this.title = title;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }
}
