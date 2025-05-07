package com.cs157.StudentPortal.repository;

import com.mysql.cj.result.Row;
import org.springframework.stereotype.Repository;
import com.cs157.StudentPortal.model.Grades;
import com.cs157.StudentPortal.model.Sections;
import com.cs157.StudentPortal.model.Students;
import com.cs157.StudentPortal.model.Courses;
import com.cs157.StudentPortal.model.Enrollment;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@Repository
public class GradesImpl implements GradesDAO{
    private final JdbcTemplate jdbcTemplate;

    public GradesImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Grades> findProfessorStudents(int ProfessorID) {
        String sql = "SELECT Courses.CourseName, Students.Name, Students.StudentID, Grades.CourseID, Grades.Grade, Grades.Units " +
                "FROM Sections " +
                "JOIN Enrollment ON Enrollment.SectionID = Sections.SectionID " +
                "JOIN Students ON Students.StudentID = Enrollment.StudentID " +
                "JOIN Courses ON Sections.CourseID = Courses.CourseID " +
                "LEFT JOIN Grades ON Grades.StudentID = Students.StudentID " +
                "AND Grades.CourseID = Sections.CourseID " +
                "WHERE Sections.ProfessorID = ? " + 
                "ORDER BY Sections.SectionID";
        
        return jdbcTemplate.query(sql, gradesRowMapper(), ProfessorID);
    }

    @Override
    public List<Grades> findByStudentId(int StudentId) {
        
        String sql = "SELECT Courses.CourseName, Grades.StudentId, Grades.CourseId, Grades.Grade, Grades.Units, Students.Name " +
                "FROM Grades " +
                "JOIN Students ON Grades.StudentID = Students.StudentID " +
                "JOIN Courses ON Courses.CourseID = Grades.CourseID " +
                "WHERE Grades.StudentID = ?";
        
        return jdbcTemplate.query(sql, gradesRowMapper(), StudentId);
    }

    @Override
    public void updateGrades(int StudentId, int SectionId, String Grade) {
        String sql = "UPDATE Grades SET Grade = ? WHERE StudentId = ?";
        jdbcTemplate.update(sql, Grade, StudentId);
    }

    private RowMapper<Grades> gradesRowMapper() {
        return (rs, rowNum) -> {
            Grades grades = new Grades();
            grades.setCourseName(rs.getString("CourseName"));
            grades.setStudentName(rs.getString("Name"));
            grades.setStudentID(rs.getInt("StudentID"));
            grades.setCourseID(rs.getInt("CourseID"));
            grades.setGrade(rs.getString("Grade"));
            grades.setUnits(rs.getInt("Units"));
            return grades;
        };
    }

}
