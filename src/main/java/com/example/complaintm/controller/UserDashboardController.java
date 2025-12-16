package com.example.complaintm.controller;

import com.example.complaintm.entity.User;
import com.example.complaintm.service.ComplaintService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserDashboardController {

    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/user/dashboard")
    public String userDashboard(HttpSession session, Model model) {

        User loggedInUser = (User) session.getAttribute("loggedUser");

        if (loggedInUser == null) {
            return "redirect:/login";
        }

        model.addAttribute(
            "complaints",
            complaintService.getComplaintsByUser(loggedInUser)
        );

        return "user-dashboard";
    }

}
