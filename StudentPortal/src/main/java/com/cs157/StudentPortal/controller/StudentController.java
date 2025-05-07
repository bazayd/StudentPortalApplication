package com.cs157.StudentPortal.controller;

import com.cs157.StudentPortal.model.Students;
import com.cs157.StudentPortal.repository.StudentsImpl;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;


@RestController
class StudentController {

    private final StudentsImpl repository;

    StudentController(StudentsImpl repository) {
        this.repository = repository;
    }


    @GetMapping("/student-details")
    Object studentSession(HttpSession session){
        var id = session.getAttribute("sessionUserID");
        if(id==null){
            return 0;
        }
        return repository.studentDetails((int)id);
    }

    

    
}
