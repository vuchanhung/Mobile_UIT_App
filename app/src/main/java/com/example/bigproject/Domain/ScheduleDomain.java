package com.example.bigproject.Domain;

public class ScheduleDomain {
    private String courseCode;
    private String session;
    private String location;
    private String teacher;

    public ScheduleDomain(String courseCode, String session, String location, String teacher) {
        this.courseCode = courseCode;
        this.session = session;
        this.location = location;
        this.teacher = teacher;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getSession() {
        return session;
    }

    public String getLocation() {
        return location;
    }

    public String getTeacher() {
        return teacher;
    }
}
