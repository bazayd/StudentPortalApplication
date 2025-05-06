package com.cs157.StudentPortal.repository;

import com.cs157.StudentPortal.model.Students;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import com.cs157.StudentPortal.model.Courses;
import com.cs157.StudentPortal.model.Sections;

import com.cs157.StudentPortal.repository.StudentsImpl;

@Repository
public class CoursesImpl implements CoursesDAO{
    private final JdbcTemplate jdbcTemplate;
    private final StudentsImpl students;

    public CoursesImpl(JdbcTemplate jdbcTemplate, StudentsImpl students) {
        this.jdbcTemplate = jdbcTemplate;
        this.students = students;
    }


    public List<Sections> getCourseBySearch(Boolean InMajor, Boolean MeetPrereq, String CourseName, int StudentID){
        Students caller = students.getStudentById(StudentID);
        int InMajorSQL = (InMajor != null && InMajor) ? 1 : 0;
        int MeetPrereqSQL = (MeetPrereq != null && MeetPrereq) ? 1 : 0;

        String sql = "SELECT Courses.CourseID, Courses.CourseName, Courses.CourseMajor, Courses.CourseUnits, Courses.CourseTitle, Courses.CourseDescription, Professors.Name, Sections.DaysOfWeek, Sections.StartTime, Sections.EndTime "
        +"FROM Sections "
        +"INNER JOIN Courses ON Sections.CourseID = Courses.CourseID "
        +"Inner JOIN Professors ON Sections.ProfessorID = Professors.ProfessorID "
        +"WHERE Courses.CourseName LIKE ? " // CourseName Search
        +"AND (?!=1 OR Courses.CourseMajor=?) " // InMajor Search
        +"AND (?!=1 OR "
            +"(SELECT count(1) FROM Requisites WHERE Requisites.ThisCourseID = Sections.CourseID) "
            +"=(SELECT count(1) FROM Requisites WHERE Requisites.ThisCourseID = Sections.CourseID AND RequiresCourseID IN ( "
                +"SELECT CourseID FROM Grades INNER JOIN Sections ON Sections.SectionID = Grades.SectionID WHERE Grades.StudentID = ? AND Completed=True AND Grade != 'F' AND Grade != 'NC')) "
        +")";

        CourseName = "%"+CourseName+"%";
        return jdbcTemplate.query(sql, sectionsRowMapper(), CourseName, InMajorSQL, caller.getMajor(), MeetPrereqSQL, StudentID);
    }

    

    @Override
    public void addCourse(Courses course) {
        String sql = "INSERT INTO Courses (CourseName, CourseUnits, CourseDescription) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, course.getCourseName(), course.getCourseUnits(), course.getCourseDescription());
    }

    @Override
    public Courses getCourseById(int CourseID) {
        String sql = "SELECT * FROM Courses WHERE CourseID = ?";
        return jdbcTemplate.queryForObject(sql, coursesRowMapper(), CourseID);
    }

    @Override
    public List<Courses> getAllCourses() {
        return List.of();
    }

    @Override
    public int deleteCourse(int CourseID) {
        return 0;
    }

    @Override
    public void updateCourse(Courses course) {

    }

    private RowMapper<Courses> coursesRowMapper() {
        return (rs, rowNum) -> {
            Courses courses = new Courses();
            courses.setCourseID(rs.getInt("CourseID"));
            courses.setCourseName(rs.getString("CourseName"));
            courses.setCourseDescription(rs.getString("CourseDescription"));
            return courses;
        };
    }


    private RowMapper<Sections> sectionsRowMapper() {
        return (rs, rowNum) -> {
            Sections sections = new Sections();
            sections.setCourseID(rs.getInt("CourseID"));
            sections.setCourseName(rs.getString("CourseName"));
            sections.setCourseDescription(rs.getString("CourseDescription"));
            sections.setCourseUnits(rs.getString("CourseUnits"));
            sections.setCourseTitle(rs.getString("CourseTitle"));
            sections.setName(rs.getString("Name"));
            sections.setStartTime(rs.getTime("StartTime"));
            sections.setEndTime(rs.getTime("EndTime"));
            sections.setDaysOfWeek(rs.getString("DaysOfWeek"));
            return sections;
        };
    }

}
