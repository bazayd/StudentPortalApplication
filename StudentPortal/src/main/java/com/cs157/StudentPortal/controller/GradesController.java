package com.cs157.StudentPortal.controller;

import com.cs157.StudentPortal.model.Grades;
import com.cs157.StudentPortal.repository.GradesImpl;
import com.cs157.StudentPortal.repository.StudentsImpl;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradesController {

    private final GradesImpl repository;

    public GradesController(GradesImpl repository) {
        this.repository = repository;
    }

    @PostMapping("/grades/{studentID}")
    public List<Grades> getGradesByStudentId(@PathVariable int StudentId) {
        return repository.findByStudentId(StudentId);
    }

    @PostMapping("/grades/{sectionId}")
    public List<Grades> getGradesBySectionId(@PathVariable int SectionId) {
        return repository.findBySectionId(SectionId);
    }


}
