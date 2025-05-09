package com.cs157.StudentPortal.model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SectionID;

    private int CourseID;

    private int StudentID;

    private String CourseName;
    private String CourseMajor;
    private String CourseUnits;
    private String CourseTitle;
    private String CourseDescription;
    private String Name;
    private Time StartTime;
    private Time EndTime;
    private String DaysOfWeek;

    public Sections(){}

    public Sections(int SectionID, int CourseID, int StudentID) {
        this.SectionID = SectionID;
        this.CourseID = CourseID;
        this.StudentID = StudentID;
    }

    public int getSectionID() {
        return SectionID;
    }

    public void setSectionID(int sectionID) {
        SectionID = sectionID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }
    public String getCourseName() {
        return CourseName;
    }
    
    public void setCourseName(String courseName) {
        CourseName = courseName;
    }
    
    public String getCourseMajor() {
        return CourseMajor;
    }
    
    public void setCourseMajor(String courseMajor) {
        CourseMajor = courseMajor;
    }
    
    public String getCourseUnits() {
        return CourseUnits;
    }
    
    public void setCourseUnits(String courseUnits) {
        CourseUnits = courseUnits;
    }
    
    public String getCourseTitle() {
        return CourseTitle;
    }
    
    public void setCourseTitle(String courseTitle) {
        CourseTitle = courseTitle;
    }
    
    public String getCourseDescription() {
        return CourseDescription;
    }
    
    public void setCourseDescription(String courseDescription) {
        CourseDescription = courseDescription;
    }
    
    public String getName() {
        return Name;
    }
    
    public void setName(String name) {
        Name = name;
    }
    
    public Time getStartTime() {
        return StartTime;
    }
    
    public void setStartTime(Time startTime) {
        StartTime = startTime;
    }
    
    public Time getEndTime() {
        return EndTime;
    }
    
    public void setEndTime(Time endTime) {
        EndTime = endTime;
    }
    
    public String getDaysOfWeek() {
        return DaysOfWeek;
    }
    
    public void setDaysOfWeek(String daysOfWeek) {
        DaysOfWeek = daysOfWeek;
    }
    
}
