package com.cs157.StudentPortal.controller;

import com.cs157.StudentPortal.repository.CoursesImpl;
import com.cs157.StudentPortal.repository.StudentsImpl;
import com.cs157.StudentPortal.model.Students;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
class CorseController {

    private final CoursesImpl repository;

    CorseController( CoursesImpl repository) {
        this.repository = repository;
    }

    @PostMapping("/get-sections")
    Object getCourses(@RequestParam(name = "InMajor", required = false) Boolean InMajor, 
                    @RequestParam(name = "MeetPrereq", required = false) Boolean MeetPrereq, 
                    @RequestParam("CourseName") String CourseName,
                    HttpSession session){
        
        var id = session.getAttribute("sessionUserID");
       
        if(id==null){
            return null;
        }
        int StudentID = (int)id;

        return repository.getCourseBySearch(InMajor, MeetPrereq, CourseName, StudentID);
    }
}