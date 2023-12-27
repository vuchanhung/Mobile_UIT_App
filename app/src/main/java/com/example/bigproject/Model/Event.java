package com.example.bigproject.Model;

public class Event {
    private String lecturerName;
    private String subjectTitle;
    private String classNumber;

    public Event() {
    }

    public Event(String lecturerName, String subjectTitle, String classNumber) {
        this.lecturerName = lecturerName;
        this.subjectTitle = subjectTitle;
        this.classNumber = classNumber;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
