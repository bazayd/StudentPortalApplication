package com.cs157.StudentPortal.controller;

import com.cs157.StudentPortal.repository.EnrollmentImpl;
import com.cs157.StudentPortal.model.Students;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
class EnrollmentController {

    private final EnrollmentImpl repository;

    EnrollmentController( EnrollmentImpl repository) {
        this.repository = repository;
    }

    @PostMapping("/register-sections")
    String getCourses(@RequestParam("SectionID") int SectionID,
                    HttpSession session){
        
        var id = session.getAttribute("sessionUserID");
        if(id==null){
            return "Please Login Before Registering For Courses";
        }
        int StudentID = (int)id;

        return repository.registerSection(SectionID, StudentID);
    }

}