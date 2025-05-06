package com.cs157.StudentPortal.repository;

import java.util.List;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.cs157.StudentPortal.model.Courses;


@Repository
public class EnrollmentImpl implements CoursesDAO{

    private final JdbcTemplate jdbcTemplate;

    public EnrollmentImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String registerSection(int SectionID, int StudentID){
        String sql = "SELECT COUNT(1) FROM Enrollment WHERE StudentID = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, StudentID);
        if(count > 0){
            return "You have already regestered for this session!";
        }

        // 


        return null;
    }








    @Override
    public void addCourse(Courses course) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCourse'");
    }

    @Override
    public Courses getCourseById(int CourseID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCourseById'");
    }

    @Override
    public List<Courses> getAllCourses() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCourses'");
    }

    @Override
    public int deleteCourse(int CourseID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCourse'");
    }

    @Override
    public void updateCourse(Courses course) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCourse'");
    }
}
