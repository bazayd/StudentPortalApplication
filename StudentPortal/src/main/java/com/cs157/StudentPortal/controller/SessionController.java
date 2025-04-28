package com.cs157.StudentPortal.controller;

import com.cs157.StudentPortal.model.Students;
import com.cs157.StudentPortal.repository.SessionRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SessionController {

    /*private final SessionRepository repository;

    SessionController(SessionRepository repository) {
        this.repository = repository;
    }*/

    @PostMapping("/student-login")
    Boolean studentLogin(@RequestParam("ID") int ID, @RequestParam("Password") String Password){
        System.out.println("Attempting Login For User ID: "+ID);
        //return repository.student();
        return true;
    }
}
