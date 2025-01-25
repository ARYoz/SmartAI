package com.example.SmartAI.Service;

import com.example.SmartAI.Model.User;
import com.example.SmartAI.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public void updateUser(Long id, String username, String password) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(username);
            user.setPassword(password);
            userRepo.save(user);
        }
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
