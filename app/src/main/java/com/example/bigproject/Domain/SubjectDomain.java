package com.example.bigproject.Domain;

public class SubjectDomain {
    private String courseName;
    private String lecturer;
    private String year;
    // Các thông tin khác tương ứng với giao diện của bạn

    public SubjectDomain(String courseName, String lecturer, String year) {
        this.courseName = courseName;
        this.lecturer = lecturer;
        this.year = year;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    // Getters and setters cho các thông tin khác nếu có
}
