package com.cs157.StudentPortal.controller;

import com.cs157.StudentPortal.repository.MessageImpl;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
class MessageController {
    private final MessageImpl repository;

    MessageController( MessageImpl repository) {
        this.repository = repository;
    }

    @PostMapping("/get-messages")
    Object getCourses(HttpSession session){
        
        var id = session.getAttribute("sessionUserID");
       
        if(id==null){
            return null;
        }
        int StudentID = (int)id;

        return repository.getMessagesForStudent(StudentID);
    }
}