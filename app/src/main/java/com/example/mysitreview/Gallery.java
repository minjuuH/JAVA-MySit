package com.example.mysitreview;

public class Gallery {
    private String imageurl;

    Gallery() {
    }

    public Gallery(String imageurl){
        this.imageurl = imageurl;
    }
    public String getImageurl(){
        return imageurl;
    }
    public void setImageurl(String iageurl){
        this.imageurl = imageurl;
    }
}