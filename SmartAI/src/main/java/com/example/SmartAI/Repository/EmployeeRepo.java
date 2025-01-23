package com.example.SmartAI.Repository;
import com.example.SmartAI.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
