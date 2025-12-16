package com.example.complaintm.controller;

import com.example.complaintm.entity.Complaint;
import com.example.complaintm.entity.User;
import com.example.complaintm.service.ComplaintService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/complaints")
public class ComplaintPageController {

    @Autowired
    private ComplaintService complaintService;

    // ================= SHOW RAISE COMPLAINT PAGE =================
    @GetMapping("/raise")
    public String showRaiseComplaintPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null || !"USER".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "raise-complaint";
    }

    // ================= HANDLE COMPLAINT SUBMISSION =================
    @PostMapping("/raise")
    public String raiseComplaint(@RequestParam String category,
                                 @RequestParam String description,
                                 HttpSession session,
                                 Model model) {

        User user = (User) session.getAttribute("loggedUser");
        if (user == null || !"USER".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }

        Complaint complaint = new Complaint();
        complaint.setCategory(category);
        complaint.setDescription(description);
        complaint.setStatus("PENDING");
        complaint.setUser(user);

        complaintService.saveComplaint(complaint);

        model.addAttribute("success", "Complaint submitted successfully!");
        model.addAttribute("user", user);
        return "raise-complaint";
    }
}
