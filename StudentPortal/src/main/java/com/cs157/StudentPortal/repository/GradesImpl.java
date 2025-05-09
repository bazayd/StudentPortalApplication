package com.cs157.StudentPortal.repository;

import com.mysql.cj.result.Row;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

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

    public boolean updateGrade(int CourseID, int StudentID, String NewGrade) {
        // Make sure new grade is a valid grade character
        if(!"ABCDFNC".contains(NewGrade)){
            return false;
        }
        // Insert the grade if it does not exist
        String sql = "INSERT INTO Grades (CourseID, StudentID, Grade) " +
                        "VALUES (?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE " +
                        "Grade = VALUES(Grade)"; 

        jdbcTemplate.update(sql, CourseID, StudentID, NewGrade);
        return true;
    }

    public boolean remHold(int StudentID){
        String sql = "UPDATE Students SET Hold = FALSE WHERE StudentID = ?";
        jdbcTemplate.update(sql, StudentID);
        return true;
    }

    public boolean addHold(int StudentID){
        String sql = "UPDATE Students SET Hold = TRUE WHERE StudentID = ?";
        jdbcTemplate.update(sql, StudentID);
        return true;
    }

    public void completeSemester(){
        // Complete all grades
        String sql = "UPDATE Grades "+
        "SET Completed=TRUE";
        jdbcTemplate.update(sql);

        // Delete all enrollments
        sql = "DELETE FROM Enrollment";
        jdbcTemplate.update(sql);
    }

    public List<Grades> findCompletedCourses(int StudentId) {
        // Only Return grades which have the completed attribute as true
        String sql = "SELECT Courses.CourseName, Grades.StudentId, Grades.CourseId, Grades.Grade, Courses.CourseUnits, Students.Name " +
                "FROM Grades " +
                "JOIN Students ON Grades.StudentID = Students.StudentID " +
                "JOIN Courses ON Courses.CourseID = Grades.CourseID " +
                "WHERE Grades.StudentID = ? AND Grades.Completed=TRUE";
        
        return jdbcTemplate.query(sql, gradesRowMapper(), StudentId);
    }

    public List<Grades> findProfessorStudents(int ProfessorID) {
        // Merge all necessary tables for all needed information, order by the section so that each class is grouped together
        String sql = "SELECT Courses.CourseName, Students.Name, Students.StudentID, Grades.CourseID, Grades.Grade, Courses.CourseUnits " +
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
        // Left JOIN grades so that if no grade has been entered yet we can still view it on frontend and set a grade (which will in turn create a grade)
        String sql = "SELECT Courses.CourseName, Enrollment.StudentId, Sections.CourseId, Grades.Grade, Courses.CourseUnits, Students.Name " +
                "FROM Enrollment " +
                "JOIN Sections ON Enrollment.SectionID = Sections.SectionID " +
                "LEFT JOIN Grades ON Grades.CourseID = Sections.CourseID AND Grades.StudentID = Enrollment.StudentID " +
                "JOIN Students ON Enrollment.StudentID = Students.StudentID " +
                "JOIN Courses ON Sections.CourseID = Courses.CourseID " +
                "WHERE Enrollment.StudentID=? " +
                "ORDER BY Grades.StudentID, Courses.CourseID";
        
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
            grades.setUnits(rs.getInt("CourseUnits"));
            return grades;
        };
    }

}
