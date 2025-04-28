package com.cs157.StudentPortal.repository;

import org.springframework.jdbc.core.JdbcTemplate; // For accessing databases through JDBC in Spring.
import org.springframework.jdbc.core.RowMapper; // mapping rows of ResultSet on per-row basis
import org.springframework.stereotype.Repository;
import java.util.List;
import com.cs157.StudentPortal.model.Students;

public class SessionRepository {
    /* 
    private final JdbcTemplate jdbcTemplate;

    public SessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    boolean studentLogin(String id, String password) {
        //String sql = "SELECT 1 FROM Students WHERE StudentID = ? AND Password = ?";
        //return jdbcTemplate.query(sql,studentsRowMapper());
        return true;
    }*/

}
