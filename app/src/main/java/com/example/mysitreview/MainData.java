package com.example.mysitreview;

public class MainData {
    private int pic;
    private String title;
    private String remain;

    //생성자
    public MainData(int pic, String title, String remain) {
        this.pic = pic;
        this.title = title;
        this.remain = remain;
    }

    //Getter
    public int getPic() {
        return pic;
    }

    public String getTitle() {
        return title;
    }

    public String getRemain() {
        return remain;
    }

    //Setter
    public void setPic(int pic) {
        this.pic = pic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }
}
