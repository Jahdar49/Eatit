package com.example.eatit.Model;

import android.media.Image;

public class Category {

    private String Name;
    private String Image;

    public Category() {

    }

    public Category(String Name, String Image) {
        Name= Name;
        Image = Image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
}