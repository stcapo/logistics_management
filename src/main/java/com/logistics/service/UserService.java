package com.logistics.service;

import com.logistics.entity.User;
import com.logistics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public User findById(Integer id) {
        return userRepository.findById(id);
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public boolean insert(User user) {
        return userRepository.insert(user) > 0;
    }
    
    public boolean update(User user) {
        return userRepository.update(user) > 0;
    }
    
    public boolean deleteById(Integer id) {
        return userRepository.deleteById(id) > 0;
    }
    
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPasswd().equals(password)) {
            return user;
        }
        return null;
    }
}
