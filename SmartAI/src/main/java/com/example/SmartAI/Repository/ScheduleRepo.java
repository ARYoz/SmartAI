package com.example.SmartAI.Repository;

import com.example.SmartAI.Model.Employee;
import com.example.SmartAI.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

}