package com.cs157.StudentPortal.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs157.StudentPortal.model.Messages;
import com.cs157.StudentPortal.model.Sections;

@Repository
public class MessageImpl{

    private final JdbcTemplate jdbcTemplate;

    public MessageImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean sendMessage(int ProfessorID, int StudentID, String MessageTitle, String MessageBody){
        String sql = "INSERT INTO Messages (ProfessorID, StudentID, MessageTitle, MessageBody) VALUES (?, ?, ?, ?)";
        return 0 < jdbcTemplate.update(sql, ProfessorID, StudentID, MessageTitle, MessageBody);
    }

    public List<Messages> getMessagesForStudent(int StudentID){
        String sql = "SELECT * FROM Messages INNER JOIN Professors ON Messages.ProfessorID = Professors.ProfessorID WHERE StudentID = ?";
        return jdbcTemplate.query(sql, MessageRowMapper(), StudentID);
    }

    public RowMapper<Messages> MessageRowMapper() {
        return (rs, rowNum) -> {
            Messages messages = new Messages();
            messages.setStudentID(rs.getInt("StudentID"));
            messages.setProfessorID(rs.getInt("ProfessorID"));
            messages.setMessageTitle(rs.getString("MessageTitle"));
            messages.setMessageDate(rs.getDate("MessageDate"));
            messages.setMessageBody(rs.getString("MessageBody"));
            messages.setProfessorName(rs.getString("Name"));
            return messages;
        };
    }

}