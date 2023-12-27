package com.example.bigproject.Model;

public class Lichthi {

    private String name;
    private String room;
    private String time;
    private String ca;

    public Lichthi() {
        // Empty constructor needed for Firestore deserialization
    }

    public Lichthi(String name, String room, String time, String ca) {
        this.name = name;
        this.room = room;
        this.time = time;
        this.ca = ca;
    }

    // Getter and Setter for 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for 'room'
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    // Getter and Setter for 'time'
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    // Getter and Setter for 'ca'
    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }
}
