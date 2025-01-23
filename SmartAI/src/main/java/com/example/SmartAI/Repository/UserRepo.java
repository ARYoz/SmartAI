package com.example.SmartAI.Repository;


import com.example.SmartAI.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}