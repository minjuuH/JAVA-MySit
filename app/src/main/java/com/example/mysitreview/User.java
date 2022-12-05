package com.example.mysitreview;

public class User {
    public String name;
    public String firstLunch;
    public String phNum;

    public User(){

    }

    public User(String name , String  firstLunch){
        this.name = name;
        this.firstLunch = firstLunch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLunch() {
        return firstLunch;
    }

    public void setFirstLunch(String firstLunch) {
        this.firstLunch = firstLunch;
    }

    public String GetPhNum(){return phNum;}

    public void setPhNum(String phNum){this.phNum = phNum;}
}
