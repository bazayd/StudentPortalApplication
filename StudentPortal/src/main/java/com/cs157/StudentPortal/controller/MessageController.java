package com.cs157.StudentPortal.controller;

import com.cs157.StudentPortal.repository.MessageImpl;
import com.cs157.StudentPortal.repository.SessionRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
class MessageController {
    private final MessageImpl repository;
    private final SessionRepository sessions;

    MessageController( MessageImpl repository, SessionRepository sessions) {
        this.repository = repository;
        this.sessions = sessions;
    }

    @PostMapping("/get-messages")
    Object getMessages(HttpSession session){
        
        var id = session.getAttribute("sessionUserID");
       
        if(id==null){
            return null;
        }
        int StudentID = (int)id;

        return repository.getMessagesForStudent(StudentID);
    }

    @PostMapping("/professor-message")
    Boolean sendMessage(@RequestParam("recipientStudentID") int StudentID, @RequestParam("messageTitle") String MessageTitle, @RequestParam("messageBody") String MessageBody, HttpSession session){
        var id = session.getAttribute("sessionProfessorID");
        if(id==null || !sessions.validateProfessorID((int)id)){
            return false;
        }
        
        return repository.sendMessage((int)id, StudentID, MessageTitle, MessageBody);
    }
}