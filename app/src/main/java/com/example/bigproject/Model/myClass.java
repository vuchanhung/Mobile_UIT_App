package com.example.bigproject.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class myClass {

    private String chat;
    private String name;
    private String teacher;
    private String semester;

    private String classNumber;
    private LocalDate date;
    private LocalTime time;
    private int year;

    public myClass() {}

    public myClass(String chat, String name, String teacher, String semester, String classNumber, LocalDate date, LocalTime time, int year) {
        this.chat = chat;
        this.name = name;
        this.teacher = teacher;
        this.semester = semester;
        this.classNumber = classNumber;
        this.date = date;
        this.time = time;
        this.year = year;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getName() {
        return name;
    }

    public String getSemester() {
        return semester;
    }

    public int getYear() {
        return year;
    }
    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
    @Override
    public String toString() {
        return "myClass{" +
                "chat='" + chat + '\'' +
                ", teacher='" + teacher + '\'' +
                "name='" + name + '\'' +
                "semester='" + semester + '\'' +
                "year='" + year + '\'' +
                '}';
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
