package com.cs157.StudentPortal.controller;

import com.cs157.StudentPortal.model.Grades;
import com.cs157.StudentPortal.repository.GradesImpl;
import com.cs157.StudentPortal.repository.StudentsImpl;
import com.cs157.StudentPortal.repository.SessionRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class GradesController {

    private final GradesImpl repository;
    private final SessionRepository sessions;

    public GradesController(GradesImpl repository, SessionRepository sessions) {
        this.repository = repository;
        this.sessions = sessions;
    }

    @PostMapping("/complete-semester")
    public void finishSemester(HttpSession session){
        var id = session.getAttribute("sessionProfessorID");
        if(id==null || !sessions.validateProfessorID((int)id)){
            return;
        }

        repository.completeSemester();
    }

    @PostMapping("/student-grades")
    public List<Grades> getGradesByStudentId(@RequestParam("StudentID") int StudentID, HttpSession session) {
        var id = session.getAttribute("sessionProfessorID");
        if(id==null || !sessions.validateProfessorID((int)id)){
            return null;
        }
        return repository.findByStudentId(StudentID);
    }

    @PostMapping("/get-students")
    public List<Grades> getProfessorStudents(HttpSession session) {
        var id = session.getAttribute("sessionProfessorID");
        if(id==null || !sessions.validateProfessorID((int)id)){
            return null;
        }
        return repository.findProfessorStudents((int)id);
    }

    @PostMapping("/get-completed")
    public List<Grades> getCompletedCourses(HttpSession session) {
        var id = session.getAttribute("sessionUserID");
        if(id==null){
            return null;
        }
        return repository.findCompletedCourses((int)id);
    }

}
