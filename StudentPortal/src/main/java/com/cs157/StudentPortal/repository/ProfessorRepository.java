package com.cs157.StudentPortal.repository;

import org.springframework.jdbc.core.JdbcTemplate; // For accessing databases through JDBC in Spring.
import org.springframework.jdbc.core.RowMapper; // mapping rows of ResultSet on per-row basis
import org.springframework.stereotype.Repository;
import java.util.List;
import com.cs157.StudentPortal.model.Students;

@Repository
public class ProfessorRepository implements StudentsDAO {
    private final JdbcTemplate jdbcTemplate;

    public ProfessorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public Students professorDetails(int ProfessorID) {
        String sql = "SELECT Name, Department, ProfessorID FROM Professors WHERE ProfessorID = ?";
        return jdbcTemplate.queryForObject(sql, studentsRowMapper(), ProfessorID);
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
    public Students findById(int StudentID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    @Override
    public List<Students> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    @Override
    public int deleteById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}
