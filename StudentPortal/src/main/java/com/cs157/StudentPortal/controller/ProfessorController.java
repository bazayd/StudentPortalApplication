package com.cs157.StudentPortal.controller;

import com.cs157.StudentPortal.model.Students;
import com.cs157.StudentPortal.repository.ProfessorsImpl;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
class ProfessorController {

    private final ProfessorsImpl repository;

    ProfessorController(ProfessorsImpl repository) {
        this.repository = repository;
    }


    @GetMapping("/professor-details")
    Object professorSession(HttpSession session){
        var id = session.getAttribute("sessionProfessorID");
        if(id==null){
            return 0;
        }
    
        return repository.professorDetails((int)id);
    }


}
