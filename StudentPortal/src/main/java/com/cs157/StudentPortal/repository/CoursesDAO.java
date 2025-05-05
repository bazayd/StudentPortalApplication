package com.cs157.StudentPortal.repository;

import com.cs157.StudentPortal.model.Courses;
import java.util.List;

public interface CoursesDAO {

    void addCourse(Courses course);

    Courses getCourseById(int CourseID);

    List<Courses> getAllCourses();

    int deleteCourse(int CourseID);

    void updateCourse(Courses course);
}
