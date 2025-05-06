package com.cs157.StudentPortal.repository;

import java.util.List;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.cs157.StudentPortal.model.Courses;
import com.cs157.StudentPortal.model.Students;


@Repository
public class EnrollmentImpl implements CoursesDAO{

    private final JdbcTemplate jdbcTemplate;
    private final StudentsImpl students;

    public EnrollmentImpl(JdbcTemplate jdbcTemplate, StudentsImpl students) {
        this.jdbcTemplate = jdbcTemplate;
        this.students = students;
    }

    public String registerSection(int SectionID, int StudentID){
        String returnMessage = "";

        // Error: Check if user has registration hold
        String sql = "SELECT COUNT(1) FROM Students WHERE StudentID = ? AND Hold = TRUE";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, StudentID);
        if(count > 0){
            return "Error: Please clear your advising hold before registering for a course!";
        }

        // Error: Check if already registered
        sql = "SELECT COUNT(1) FROM Enrollment WHERE StudentID = ? AND SectionID = ?";
        count = jdbcTemplate.queryForObject(sql, Integer.class, StudentID, SectionID);
        if(count > 0){
            return "Error: You have already regestered for this session!";
        }
        
        // Warning: Check if section has time conflict with already registered sections

        /* Time Overlap
         * Example1:
         *      s1      e1
         *   s2     e2
         * Example2:
         *      s1      e1
         *   s2             e2
         * Example3:
         *      s1      e1
         *          s2      e2
         */
        // See if we can select a Enrolled section that has a time conflict
        sql = "SELECT COUNT(1) "
        +"FROM Sections AS NewSection "
        +"WHERE SectionID = ? "
        +"AND EXISTS (SELECT 1 "
        +"FROM Enrollment "
        +"INNER JOIN Sections "
        +"ON Enrollment.SectionID = Sections.SectionID "
        +"WHERE Enrollment.StudentID = ? "
        +"AND NewSection.DaysOfWeek = Sections.DaysOfWeek " // Check Same Days. Then Check times.
        +"AND ((NewSection.StartTime <= Sections.StartTime AND NewSection.EndTime > Sections.StartTime) OR (NewSection.StartTime < Sections.EndTime AND NewSection.EndTime >= Sections.EndTime))"
        +")";

        count = jdbcTemplate.queryForObject(sql, Integer.class, SectionID, StudentID);
        if(count > 0){
            returnMessage = returnMessage+"Warning: Your Selected Section Has A Time Conflict With Existing Registered Sections.\n";
        }

        // Warning: Registering for course outside of major

        // Get student object
        Students student = students.getStudentById(StudentID);
        
        sql = "SELECT COUNT(1) "
        +"FROM Sections "
        +"INNER JOIN Courses ON Sections.CourseID = Courses.CourseID "
        +"WHERE Sections.SectionID = ? "
        +"AND Courses.CourseMajor = ? ";

        count = jdbcTemplate.queryForObject(sql, Integer.class, SectionID, student.getMajor());
        if(count == 0){
            returnMessage = returnMessage+"Warning: This Course Not Under Your Department.\n";
        }



        // Insert New Enrollment
        sql = "INSERT INTO Enrollment (StudentID, SectionID) VALUES (?,?)";
        count = jdbcTemplate.update(sql, StudentID, SectionID);
        
        returnMessage+="Successfully Registered New Section!";
        return returnMessage;
    }








    @Override
    public void addCourse(Courses course) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCourse'");
    }

    @Override
    public Courses getCourseById(int CourseID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCourseById'");
    }

    @Override
    public List<Courses> getAllCourses() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCourses'");
    }

    @Override
    public int deleteCourse(int CourseID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCourse'");
    }

    @Override
    public void updateCourse(Courses course) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCourse'");
    }
}
