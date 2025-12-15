package com.example.complaintm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.complaintm.entity.Complaint;
import com.example.complaintm.repository.ComplaintRepository;

@Service
public class AdminService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public void resolveComplaint(Long id) {
        Complaint complaint = complaintRepository.findById(id).orElse(null);
        if (complaint != null) {
            complaint.setStatus("RESOLVED");
            complaintRepository.save(complaint);
        }
    }
}
