package com.example.bigproject.Model;

public class User {
    private String name;
    private String MSSV;
    private String password;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name, String MSSV, String password) {
        this.name = name;
        this.MSSV = MSSV;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getMssv() {
        return MSSV;
    }

    public String getPassword() {
        return password;
    }
}