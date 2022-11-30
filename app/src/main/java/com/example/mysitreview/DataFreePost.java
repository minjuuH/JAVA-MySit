package com.example.mysitreview;

public class DataFreePost {
    private int pic;
    private String title;
    private String introduce;
    private String deadline;
    private String remain;

    //생성자
    public DataFreePost(int pic, String title, String introduce, String deadline, String remain) {
        this.pic = pic;
        this.title = title;
        this.introduce = introduce;
        this.deadline = deadline;
        this.remain = remain;
    }

    //Getter
    public int getPic() {
        return pic;
    }
    public String getTitle() {
        return title;
    }
    public String getIntroduce() { return this.introduce; }
    public String getDeadline() { return deadline; }
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
    public void setIntroduce(String introduce) { this.introduce = introduce; }
    public void setDeadline(String deadline) { this.deadline = deadline; }
    public void setRemain(String remain) {
        this.remain = remain;
    }
}
