package com.cs157.StudentPortal.repository;

import org.springframework.jdbc.core.JdbcTemplate; // For accessing databases through JDBC in Spring.
import org.springframework.jdbc.core.RowMapper; // mapping rows of ResultSet on per-row basis
import org.springframework.stereotype.Repository;
import java.util.List;
import com.cs157.StudentPortal.model.Students;

@Repository
public class StudentImpl implements StudentsDAO{
    private final JdbcTemplate jdbcTemplate;

    public StudentImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    private RowMapper<Students> studentsRowMapper() {
        return (rs, rowNum) -> {
            Students students = new Students();
            students.setStudentID(rs.getInt("StudentID"));
            students.setName(rs.getString("Name"));
            students.setMajor(rs.getString("Major"));
            return students;
        };
    }

    @Override
    public void addStudent(Students student) {
        String sql = "INSERT INTO Students (name, major) VALUES (?, ?)";
        jdbcTemplate.update(sql, student.getName(), student.getMajor());
    }

    @Override
    public Students getStudentById(int StudentID) {
        String sql = "SELECT * FROM Students WHERE StudentID = ?";
        return jdbcTemplate.queryForObject(sql, studentsRowMapper(), StudentID);
    }

    @Override
    public List<Students> getAllStudents() {
        String sql = "SELECT * FROM Students";
        return jdbcTemplate.query(sql, studentsRowMapper());
    }

    @Override
    public int deleteStudent(int StudentID) {
        String sql = "DELETE FROM Students WHERE StudentID = ?";
        return jdbcTemplate.update(sql, StudentID);
    }

    @Override
    public void updateStudent(Students student) {
        String sql = "UPDATE Students SET Name = ?, Major = ? WHERE StudentID = ?";
        jdbcTemplate.update(sql, student.getName(), student.getMajor(), student.getStudentID());
    }
}


