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

    @PostMapping("/student-grades")
    public List<Grades> getGradesByStudentId(@RequestParam("StudentID") int StudentID, HttpSession session) {
        var id = session.getAttribute("sessionProfessorID");
        if(id==null || !sessions.validateProfessorID((int)id)){
            return null;
        }
        return repository.findByStudentId(StudentID);
    }

    /* 
    @PostMapping("/grades/{sectionId}")
    public List<Grades> getGradesBySectionId(@PathVariable int SectionId) {
        return repository.findBySectionId(SectionId);
    }
        */


}
