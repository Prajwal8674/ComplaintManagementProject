package com.example.complaintm.controller;

import com.example.complaintm.entity.User;
import com.example.complaintm.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthPageController {

    @Autowired
    private UserService userService;

    // ================= LOGIN PAGE =================
    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        session.invalidate(); // clear old session
        return "login";
    }

    // ================= LOGIN PROCESS =================
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String role,
                            HttpSession session,
                            Model model) {

        User user = userService.login(email, password);

        if (user == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        if (!user.getRole().equalsIgnoreCase(role)) {
            model.addAttribute("error", "Role mismatch");
            return "login";
        }

        // Store user in session
        session.setAttribute("loggedUser", user);

        // Redirect based on role
        if (user.getRole().equalsIgnoreCase("ADMIN")) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/complaints/raise";
        }
    }

    // ================= REGISTER =================
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        userService.register(user);
        model.addAttribute("success", "Registration successful! You can login now.");
        return "register";
    }

    // ================= LOGOUT =================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
