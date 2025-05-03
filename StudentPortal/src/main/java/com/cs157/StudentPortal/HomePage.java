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
        return "redirect:/studentLogin.html";
    }

}