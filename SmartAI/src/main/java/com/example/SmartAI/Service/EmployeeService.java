package com.example.SmartAI.Service;


import com.example.SmartAI.Model.Employee;
import com.example.SmartAI.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo)
    {
        this.employeeRepo = employeeRepo;
    }
    public Employee addEmployee(Employee employee) //POST
    {
        if(employeeRepo.existsById(employee.getIdNumber()))
        {
            throw new RuntimeException("Employee with ID:" + employee.getIdNumber() + "alredy exists.");
        }
        return employeeRepo.save(employee);
    }

    public List<Employee>getAllEmployees() // GET ALL EMPLOYEES
    {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeByID(Long id) // Get employee by ID
    {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not fount with ID:" + id));
    }

    public Employee UpdateEmployee(Long id, Employee UpdateEmployee) //UPDATE Employee
    {
        Employee exisitsEmployee = employeeRepo.getById(id);

        if(exisitsEmployee == null)
        {
            throw new RuntimeException("This employee not fount with this Id:" + id);
        }

        exisitsEmployee.setFirstName(UpdateEmployee.getFirstName());
        exisitsEmployee.setLastName(UpdateEmployee.getLastName());
        exisitsEmployee.setIdNumber(UpdateEmployee.getIdNumber());
        exisitsEmployee.setMarried(UpdateEmployee.isMarried());
        exisitsEmployee.setHaveChildren(UpdateEmployee.isHaveChildren());
        exisitsEmployee.setManager(UpdateEmployee.isManager());

        return employeeRepo.save(exisitsEmployee);
    }

    public void DeteteEmployee(Long id) // Delete Employee
    {

        employeeRepo.deleteById(id);
    }

}