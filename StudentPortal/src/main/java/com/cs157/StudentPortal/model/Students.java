package com.cs157.StudentPortal.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int StudentID;


    private String Name;


    private String Major;

    private Boolean Hold;

    public Students(int StudentID, String Name, String Major, Boolean Hold) {
        this.StudentID = StudentID;
        this.Name = Name;
        this.Major = Major;
        this.Hold = Hold;
    }

    public Students() {
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public Boolean getHold() {
        return Hold;
    }

    public void setHold(Boolean hold) {
        Hold = hold;
    }

    @Override
    public String toString() {
        return String.format(
                "Student[Student ID=%d, Name='%s', Major='%s']",
                StudentID, Name, Major
        );
    }
}
