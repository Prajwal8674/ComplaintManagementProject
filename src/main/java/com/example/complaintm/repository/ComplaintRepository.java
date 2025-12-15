package com.example.complaintm.repository;

import com.example.complaintm.entity.Complaint;
import com.example.complaintm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUser(User user);
}
