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

    @PostMapping("/get-hold-status")
    public String getHoldStatus(@RequestParam("StudentID") int StudentID, HttpSession session) {
        var id = session.getAttribute("sessionProfessorID");
        if (id == null || !sessions.validateProfessorID((int) id)) {
            return null; // optionally handle this case with a specific HTTP status
        }

        return repository.getHoldStatus(StudentID);
    }


    @PostMapping("/add-hold")
    public void addHold(@RequestParam("StudentID") int StudentID,  HttpSession session) {
        var id = session.getAttribute("sessionProfessorID");
        if (id == null || !sessions.validateProfessorID((int) id)) {
            return;
        }

        repository.addHold(StudentID);
    }

    @PostMapping("/remove-hold")
    public void removeHold(@RequestParam("StudentID") int StudentID, HttpSession session) {
        var id = session.getAttribute("sessionProfessorID");
        if (id == null || !sessions.validateProfessorID((int) id)) {
            return;
        }

        repository.removeHold(StudentID);
    }

    @PostMapping("/student-grades")
    public List<Grades> getGradesByStudentId(@RequestParam("StudentID") int StudentID, HttpSession session) {
        var id = session.getAttribute("sessionProfessorID");
        if(id==null || !sessions.validateProfessorID((int)id)){
            return null;
        }

        return repository.findByStudentId(StudentID);
    }

    @PostMapping("/update-student-grade")
    public void updateStudentGrade(
            @RequestParam("StudentID") int StudentID,
            @RequestParam("SectionID") int SectionID,
            @RequestParam("Grade") String Grade,
            HttpSession session) {

        var id = session.getAttribute("sessionProfessorID");
        if (id == null || !sessions.validateProfessorID((int) id)) {
            return; // Alternatively, consider throwing an exception or returning an HTTP error if needed
        }

        repository.updateGrades(StudentID, SectionID, Grade);
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
