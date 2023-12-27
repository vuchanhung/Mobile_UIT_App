package com.example.bigproject.Model;

import com.google.firebase.firestore.PropertyName;

public class News {
    @PropertyName("id")
    private String id;
    @PropertyName("title")
    private String title;

    @PropertyName("content")
    private String content;

    @PropertyName("imageUrl")
    private String imageUrl;

    public News() {
        // Empty constructor needed for Firestore deserialization

    }

    public News(String id, String title, String content, String imageUrl) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    @PropertyName("id")
    public String getId() {
        return id;
    }

    @PropertyName("id")
    public void setId(String id) {
        this.id = id;
    }


    @PropertyName("title")
    public String getTitle() {
        return title;
    }

    @PropertyName("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @PropertyName("content")
    public String getContent() {
        return content;
    }

    @PropertyName("content")
    public void setContent(String content) {
        this.content = content;
    }

    @PropertyName("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    @PropertyName("imageUrl")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
