package com.cs157.StudentPortal.repository;

import com.cs157.StudentPortal.model.Students;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.cs157.StudentPortal.model.Courses;

@Repository
public class CoursesImpl implements CoursesDAO{
    private final JdbcTemplate jdbcTemplate;

    public CoursesImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addCourse(Courses course) {
        String sql = "INSERT INTO Courses (CourseName, CourseUnits, CourseDescription) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, course.getCourseName(), course.getCourseUnits(), course.getCourseDescription());
    }

    @Override
    public Courses getCourseById(int CourseID) {
        String sql = "SELECT * FROM Courses WHERE CourseID = ?";
        return jdbcTemplate.queryForObject(sql, coursesRowMapper(), CourseID);
    }

    @Override
    public List<Courses> getAllCourses() {
        return List.of();
    }

    @Override
    public int deleteCourse(int CourseID) {
        return 0;
    }

    @Override
    public void updateCourse(Courses course) {

    }

    private RowMapper<Courses> coursesRowMapper() {
        return (rs, rowNum) -> {
            Courses courses = new Courses();
            courses.setCourseID(rs.getInt("CourseID"));
            courses.setCourseName(rs.getString("CourseName"));
            courses.setCourseDescription(rs.getString("CourseDescription"));
            return courses;
        };
    }

}
