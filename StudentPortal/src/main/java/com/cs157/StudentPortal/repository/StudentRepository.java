package com.cs157.StudentPortal.repository;

import org.springframework.jdbc.core.JdbcTemplate; // For accessing databases through JDBC in Spring.
import org.springframework.jdbc.core.RowMapper; // mapping rows of ResultSet on per-row basis
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import com.cs157.StudentPortal.model.Students;

@Repository
public class StudentRepository implements StudentsDAO{
    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Students studentDetails(int StudentID) {
        String sql = "SELECT StudentID, Name, Major FROM Students WHERE StudentID = ?";
        return jdbcTemplate.queryForObject(sql, studentsRowMapper(), StudentID);
    }
    
    public Students professorDetails(int ProfessorID) {
        String sql = "SELECT Professors, Name, Department FROM Students WHERE StudentID = ?";
        return jdbcTemplate.queryForObject(sql, studentsRowMapper(), ProfessorID);
    }

    @Override
    public Students findById(int StudentID) {
        String sql = "SELECT StudentID, Name, Major FROM Students WHERE StudentID = ?";
        return jdbcTemplate.queryForObject(sql, studentsRowMapper(), StudentID);
    }

    public List<Students> findAll() {
        String sql = "SELECT * FROM Students";
        return jdbcTemplate.query(sql,studentsRowMapper());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM Students WHERE StudentID = ?";
        return jdbcTemplate.update(sql, id);
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
}

