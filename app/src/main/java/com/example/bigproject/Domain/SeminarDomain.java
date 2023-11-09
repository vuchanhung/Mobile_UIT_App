package com.example.bigproject.Domain;

public class SeminarDomain {

    private String title;
    private String subtitle;
    private String seminar_image;

    public SeminarDomain(String title, String subtitle, String seminar_image) {
        this.title = title;
        this.subtitle = subtitle;
        this.seminar_image = seminar_image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPicAddress() {
        return seminar_image;
    }

    public void setPicAddress(String seminar_image) {
        this.seminar_image = seminar_image;
    }
}
