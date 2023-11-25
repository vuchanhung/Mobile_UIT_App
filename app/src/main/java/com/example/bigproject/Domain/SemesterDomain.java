package com.example.bigproject.Domain;

public class SemesterDomain {
    public String class_code;
    public String class1;
    public int credit;
    public float progress;
    public float practice;
    public float mid_term;
    public float end_term;
    public float average_score;


    public SemesterDomain(String class_code, String class1, int credit, float progress, float practice, float mid_term, float end_term, float average_score){
        this.class_code = class_code;
        this.class1 = class1;
        this.credit = credit;
        this.progress = progress;
        this.practice = practice;
        this.mid_term = mid_term;
        this.end_term = end_term;
        this.average_score = average_score;
    }

    public String getClass_code() {
        return class_code;
    }

    public String getClass1() {
        return class1;
    }

    public int getCredit() {
        return credit;
    }

    public float getProgress() {
        return progress;
    }

    public float getPractice() {
        return practice;
    }

    public float getMid_term() {
        return mid_term;
    }

    public float getEnd_term() {
        return end_term;
    }

    public float getAverage_score() {
        return average_score;
    }
}