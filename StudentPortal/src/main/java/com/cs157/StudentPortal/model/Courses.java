package com.cs157.StudentPortal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CourseID;

    private String CourseName;

    private int CourseUnits;

    private String CourseDescription;

    public Courses(){}

    public Courses(int CourseId, String CourseName, int CourseUnits, String CourseDescription) {
        this.CourseID = CourseId;
        this.CourseName = CourseName;
        this.CourseUnits = CourseUnits;
        this.CourseDescription = CourseDescription;
    }


    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public int getCourseUnits() {
        return CourseUnits;
    }

    public void setCourseUnits(int courseUnits) {
        CourseUnits = courseUnits;
    }

    public String getCourseDescription() {
        return CourseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        CourseDescription = courseDescription;
    }
}
