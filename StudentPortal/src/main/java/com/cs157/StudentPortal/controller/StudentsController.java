package com.cs157.StudentPortal.controller;

import com.cs157.StudentPortal.model.Students;
import com.cs157.StudentPortal.repository.StudentRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
class StudentsController {

    private final StudentRepository repository;

    StudentsController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/students")
    List<Students> all(){
        System.out.println("Retrieving All Students");
        return repository.findAll();
    }

    
}
