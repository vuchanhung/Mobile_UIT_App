package com.example.bigproject.Model;

import java.util.ArrayList;

public class User {
    private String name;
    private String MSSV;
    private String password;

    private String Birthday;

    private String TC;
    private String address;

    private ArrayList<String> enrolledClass;
    private ArrayList<String> enrolledGroup;


    public User() {
        enrolledClass = new ArrayList<>();
        enrolledGroup = new ArrayList<>();
    }

    public User(String name, String MSSV, String password, String birthday, String TC, String address) {
        this.name = name;
        this.MSSV = MSSV;
        this.password = password;
        this.Birthday = birthday;
        this.TC = TC;
        this.address = address;
        enrolledClass = new ArrayList<>();
        enrolledGroup = new ArrayList<>();
    }
    public User(String name, String MSSV, String password) {
        this.name = name;
        this.MSSV = MSSV;
        this.password = password;
    }
    public ArrayList<String> getEnrolledClass() {
        return enrolledClass;
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