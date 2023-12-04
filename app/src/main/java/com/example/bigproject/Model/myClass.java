package com.example.bigproject.Model;

public class myClass {

    private String chat;
    private String name;
    private String teacher;
    private String semester;
    private int year;

    public myClass() {}

    public myClass(String chat, String name, String teacher, String semester, int year) {
        this.chat = chat;
        this.name = name;
        this.teacher = teacher;
        this.semester = semester;
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
}
