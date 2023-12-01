package com.example.bigproject.Domain;

public class SubjectDomain {
    private String courseName;
    private String lecturer;
    private String semester;
    // Các thông tin khác tương ứng với giao diện của bạn

    public SubjectDomain(String semester, String courseName, String lecturer) {
        this.semester = semester;
        this.courseName = courseName;
        this.lecturer = lecturer;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    // Getters and setters cho các thông tin khác nếu có
}
