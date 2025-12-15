package com.example.complaintm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.complaintm.entity.Complaint;
import com.example.complaintm.entity.User;
import com.example.complaintm.service.ComplaintService;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping
    public Complaint raiseComplaint(@RequestBody Complaint complaint) {
        return complaintService.saveComplaint(complaint);
    }

    @PostMapping("/user")
    public List<Complaint> getUserComplaints(@RequestBody User user) {
        return complaintService.getUserComplaints(user);
    }
}
