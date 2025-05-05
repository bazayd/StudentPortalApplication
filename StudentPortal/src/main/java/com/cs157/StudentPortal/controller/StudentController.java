package com.cs157.StudentPortal.controller;

import com.cs157.StudentPortal.model.Students;
import com.cs157.StudentPortal.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
class StudentController {

    private final StudentRepository repository;

    StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/students")
    List<Students> all(){
        System.out.println("Retrieving All Students");
        return repository.findAll();
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
