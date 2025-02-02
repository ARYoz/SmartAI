package com.example.SmartAI.Controller;


import com.example.SmartAI.Model.Employee;
import com.example.SmartAI.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping // Add a new Employee
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
    {
        Employee newEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.ok(newEmployee);
    }

    @GetMapping // Get a one employee by his ID
    public ResponseEntity<Employee> getEmployeeById(@RequestBody Long id)
    {
        Employee employee = employeeService.getEmployeeByID(id);
        return ResponseEntity.ok(employee);
    }
    @GetMapping // Get all the Employees
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id , @RequestBody Employee UpdateEmployee)
    {
        Employee employee = employeeService.UpdateEmployee(id,UpdateEmployee);
        return ResponseEntity.ok(employee);
    }

    @Deprecated
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id)
    {
        employeeService.DeteteEmployee(id);
        return ResponseEntity.ok("Employee with ID:" + id + "deleted successfully.");
    }

}
