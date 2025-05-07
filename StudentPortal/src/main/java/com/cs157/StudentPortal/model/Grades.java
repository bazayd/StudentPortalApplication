package com.cs157.StudentPortal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String CourseName;
    private String StudentName;

    private int StudentID;

    private int SectionID;

    private String Grade;

    private int Units;

    public Grades() {}

    public Grades(int StudentID, int SectionID, String Grade, int Units) {
        this.StudentID = StudentID;
        this.SectionID = SectionID;
        this.Grade = Grade;
        this.Units = Units;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }
    
    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public int getSectionID() {
        return SectionID;
    }

    public void setSectionID(int sectionID) {
        SectionID = sectionID;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public int getUnits() {
        return Units;
    }

    public void setUnits(int units) {
        Units = units;
    }
}
