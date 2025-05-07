package com.cs157.StudentPortal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomePage {

    @GetMapping("/")
    public String greeting() {
        return "redirect:/dashboard.html";
    }

    @GetMapping("/studentLogin")
    public String sLogin() {
        return "redirect:/studentLogin.html";
    }

    @GetMapping("/professorLogin")
    public String pLogin() {
        return "redirect:/professorLogin.html";
    }

    @GetMapping("/studentRegister")
    public String sRegister() {
        return "redirect:/studentRegister.html";
    }

    @GetMapping("/professorRegister")
    public String pRegister() {
        return "redirect:/professorRegister.html";
    }

    @GetMapping("/courses")
    public String courses() {
        return "redirect:/courses.html";
    }

    @GetMapping("/admin")
    public String admin() {
        return "redirect:/professorAdmin.html";
    }
    
    @GetMapping("/messages")
    public String messages() {
        return "redirect:/messages.html";
    }

    @GetMapping("/courseHistory")
    public String courseHistory() {
        return "redirect:/courseHistory.html";
    }

    @GetMapping("/professorMessaging")
    public String professorMessaging() {
        return "redirect:/professorMessaging.html";
    }
}