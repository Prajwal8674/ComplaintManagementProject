package com.example.complaintm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // Change this from @RestController to @Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";  // Spring will now look for templates/index.html
    }
}
