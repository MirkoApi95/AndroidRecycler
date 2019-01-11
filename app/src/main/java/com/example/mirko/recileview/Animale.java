package com.example.mirko.recileview;

import android.net.Uri;

public class Animale {

    private String razza;
    private Uri img;

    public Animale() {
    }

    public Animale(String razza, Uri img) {
        this.razza = razza;
        this.img = img;
    }

    public String getRazza() {
        return razza;
    }

    public void setRazza(String razza) {
        this.razza = razza;
    }

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }
}
