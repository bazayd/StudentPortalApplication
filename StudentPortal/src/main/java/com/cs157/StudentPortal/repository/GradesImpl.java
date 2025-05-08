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

    public void completeSemester(){
        // Complete all grades
        String sql = "UPDATE Grades "+
        "SET Completed=TRUE";
        jdbcTemplate.update(sql);

        // Delete all enrollments
        sql = "DELETE FROM Enrollment";
        jdbcTemplate.update(sql);
    }

    public void addHold(int StudentId) {
        String sql = "UPDATE Grades "+
        "SET Hold= TRUE "+
                "WHERE StudentID = ?";
        jdbcTemplate.update(sql, studentsRowMapper(), StudentId);
    }

    public void removeHold(int StudentID) {
        String sql = "UPDATE Students "+
        "SET Hold= FALSE "+
                "WHERE StudentID = ?";
        jdbcTemplate.update(sql, studentsRowMapper(), StudentID);
    }

    public String getHoldStatus(int StudentID) {
        String sql = "SELECT Hold FROM Students WHERE StudentID = ?";
        return jdbcTemplate.queryForObject(sql, String.class, StudentID);
    }

    public List<Grades> findCompletedCourses(int StudentId) {
        
        String sql = "SELECT Courses.CourseName, Grades.StudentId, Grades.CourseId, Grades.Grade, Grades.Units, Students.Name " +
                "FROM Grades " +
                "JOIN Students ON Grades.StudentID = Students.StudentID " +
                "JOIN Courses ON Courses.CourseID = Grades.CourseID " +
                "WHERE Grades.StudentID = ? AND Grades.Completed=TRUE";
        System.out.println(sql);
        return jdbcTemplate.query(sql, gradesRowMapper(), StudentId);
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
        
        String sql = "SELECT Courses.CourseName, Grades.StudentId, Grades.CourseId, Grades.Grade, Grades.Units, Students.Name, Students.Hold " +
                "FROM Grades " +
                "JOIN Students ON Grades.StudentID = Students.StudentID " +
                "JOIN Courses ON Courses.CourseID = Grades.CourseID " +
                "WHERE Grades.StudentID = ? AND Grades.Completed=FALSE";
        
        return jdbcTemplate.query(sql, gradesRowMapper(), StudentId);
    }

    @Override
    public void updateGrades(int StudentId, int CourseId, String Grade) {
        String sql = "UPDATE Grades SET Grade = ? WHERE StudentId = ? AND CourseId = ?";
        jdbcTemplate.update(sql, Grade, StudentId, CourseId);
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

    private RowMapper<Students> studentsRowMapper() {
        return (rs, rowNum) -> {
            Students students = new Students();
            students.setStudentID(rs.getInt("StudentID"));
            students.setName(rs.getString("Name"));
            students.setMajor(rs.getString("Major"));
            students.setHold(rs.getBoolean("Hold"));
            return students;
        };
    }
}
