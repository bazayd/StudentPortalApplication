package com.cs157.StudentPortal.repository;

import org.springframework.jdbc.core.JdbcTemplate; // For accessing databases through JDBC in Spring.
import org.springframework.jdbc.core.RowMapper; // mapping rows of ResultSet on per-row basis
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Statement;  // 

import java.sql.PreparedStatement;
import java.util.List;
import com.cs157.StudentPortal.model.Students;

@Repository
public class SessionRepository {
    private final JdbcTemplate jdbcTemplate;

    public SessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean studentLogin(int id, String password) {
        String sql = "SELECT COUNT(*) FROM Students WHERE StudentID = ? AND Password = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id, password);
        return count != null && count > 0;
    }

    public boolean professorLogin(int id, String password) {
        String sql = "SELECT COUNT(*) FROM Professors WHERE ProfessorID = ? AND Password = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id, password);
        return count != null && count > 0;
    }

    public boolean validateProfessorID(int id) {
        String sql = "SELECT COUNT(*) FROM Professors WHERE ProfessorID = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }


    public Integer studentRegister(String name, String major, String password) { 
        String sql = "INSERT INTO Students (Name, Major, Password) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int count = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, major);
            ps.setString(3, password);
            return ps;
        }, keyHolder);

        if (count > 0) {
            Number key = keyHolder.getKey();
            if (key != null) {
                return key.intValue();
            }
        }
        return -1;
    }

    public Integer professorRegister(String name, String department, String password) {
        String sql = "INSERT INTO Professors (Name, Department, Password) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int count = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, department);
            ps.setString(3, password);
            return ps;
        }, keyHolder);

        if (count > 0) {
            Number key = keyHolder.getKey();
            if (key != null) {
                return key.intValue();
            }
        }
        return -1;
    }

}
