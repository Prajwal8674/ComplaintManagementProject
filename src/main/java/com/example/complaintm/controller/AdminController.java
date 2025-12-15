package com.example.complaintm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.complaintm.entity.Complaint;
import com.example.complaintm.service.ComplaintService;

@Controller
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/complaints")
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @PutMapping("/resolve/{id}")
    public void resolveComplaint(@PathVariable Long id) {
        complaintService.resolveComplaint(id);
    }
}
