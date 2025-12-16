package com.example.complaintm.controller;

import com.example.complaintm.entity.User;
import com.example.complaintm.service.ComplaintService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ComplaintService complaintService;

    // ================= ADMIN DASHBOARD =================
    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedUser");
        if (loggedInUser == null || !"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
            return "redirect:/login";
        }

        model.addAttribute("complaints", complaintService.getAllComplaints());
        return "admin-dashboard";
    }

    // ================= RESOLVE COMPLAINT =================
    @PostMapping("/resolve/{id}")
    public String resolveComplaint(@PathVariable Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedUser");
        if (loggedInUser == null || !"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
            return "redirect:/login";
        }

        complaintService.resolveComplaint(id);
        return "redirect:/admin/dashboard";
    }
}
