package com.example.bigproject.Model;

public class Message {
    private String senderId;
    private String text;
    private long timestamp;

    public Message() {
        // Default constructor required for Firestore
    }

    public Message(String senderId, String text, long timestamp) {
        this.senderId = senderId;
        this.text = text;
        this.timestamp = timestamp;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getText() {
        return text;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

