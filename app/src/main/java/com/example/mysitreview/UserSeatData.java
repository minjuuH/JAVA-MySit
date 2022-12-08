package com.example.mysitreview;

public class UserSeatData {
    private String title;
    private String rdate;
    private String  type;

    //생성자
    public UserSeatData(String title, String rdate) {
        this.title = title;
        this.rdate = rdate;
        this.type = type;
    }

    //Getter
    public String getTitle() {
        return title;
    }

    public String getRdate() {
        return rdate;
    }

    //Setter
    public void setTitle(String title) {
        this.title = title;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }


    public String getType(){return type;}

    public void setType(String type){ this.type = type;}


}
