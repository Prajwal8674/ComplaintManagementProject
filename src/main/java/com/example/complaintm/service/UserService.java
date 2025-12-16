package com.example.complaintm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.complaintm.entity.User;
import com.example.complaintm.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register user (role is set from form)
    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return null; // Email exists
        }
        return userRepository.save(user);
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
