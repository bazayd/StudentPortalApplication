package com.cs157.StudentPortal.controller;

import com.cs157.StudentPortal.model.Students;
import com.cs157.StudentPortal.repository.SessionRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SessionController {

    private final SessionRepository repository;

    SessionController(SessionRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/student-login")
    String studentLogin(@RequestParam("ID") int ID, @RequestParam("Password") String Password, HttpSession session){
        if(repository.studentLogin(ID, Password)){
            session.setAttribute("sessionUserID", ID);
        } else {
            session.invalidate();
        }
        var redirectTo = "studentLogin.html";
        return "<script>window.location.href = \""+redirectTo+"\";</script>";
    }

    @PostMapping("/professor-login")
    String professorLogin(@RequestParam("ID") int ID, @RequestParam("Password") String Password, HttpSession session){
        if(repository.professorLogin(ID, Password)){
            session.setAttribute("sessionUserID", ID);
        } else {
            session.invalidate();
        }
        var redirectTo = "professorLogin.html";
        return "<script>window.location.href = \""+redirectTo+"\";</script>";
    }

}
