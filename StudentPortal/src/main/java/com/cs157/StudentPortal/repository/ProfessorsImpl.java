package com.cs157.StudentPortal.repository;

import com.cs157.StudentPortal.model.Professors;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;


@Repository
public class ProfessorsImpl implements ProfessorsDAO{
    private final JdbcTemplate jdbcTemplate;

    public ProfessorsImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Professors professorDetails(int ProfessorID) {
        String sql = "SELECT Name, Department, ProfessorID FROM Professors WHERE ProfessorID = ?";
        return jdbcTemplate.queryForObject(sql, professorsRowMapper(), ProfessorID);
    }


    private RowMapper<Professors> professorsRowMapper() {
        return (rs, rowNum) -> {
            Professors professor = new Professors();
            professor.setProfessorID(rs.getInt("ProfessorID"));
            professor.setName(rs.getString("Name"));
            professor.setDepartment(rs.getString("Department"));
            return professor;
        };
    }


    @Override
    public void addProfessor(Professors professor) {
        String sql = "INSERT INTO Professors (Name, Password, Department) VALUES (?,?,?)";
        jdbcTemplate.update(sql, professor.getName(), professor.getDepartment());
    }

    @Override
    public Professors getProfessorById(int ProfessorID) {
        String sql = "SELECT * FROM Professors WHERE ProfessorID = ?";
        return jdbcTemplate.queryForObject(sql, professorsRowMapper(), ProfessorID);
    }

    @Override
    public List<Professors> getAllProfessors() {
        String sql = "SELECT * FROM Professors";
        return jdbcTemplate.query(sql, professorsRowMapper());
    }

    @Override
    public int deleteProfessor(int ProfessorID) {
        String sql = "DELETE FROM Professors WHERE ProfessorID = ?";
        return jdbcTemplate.update(sql, ProfessorID);
    }

    @Override
    public void updateProfessor(Professors professor) {
        String sql = "UPDATE Professors SET Name = ?, Department = ? WHERE ProfessorID = ?";
        jdbcTemplate.update(sql, professor.getName(), professor.getDepartment(), professor.getProfessorID());
    }

}
