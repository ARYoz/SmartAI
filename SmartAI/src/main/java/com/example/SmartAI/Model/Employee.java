package com.example.SmartAI.Model;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Employee {

    @Id
    @NotNull
    @Size(min = 9, max = 9, message = "ID Number must be 9 digits")
    private Long idNumber;
    private String FirstName;
    private String LastName;
    private boolean isMarried;
    private boolean haveChildren;
    private boolean isManager;

    private String role; // Manager, Employee, etc.


    @OneToMany
    private List<Constraint_Employee> constraints; // Employee's constraints

    @ManyToMany(mappedBy = "employees")
    private List<com.example.SmartAI.Model.Shift> shifts; // Shifts assigned to the employee

    //Geters and Seters
    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public boolean isHaveChildren() {
        return haveChildren;
    }

    public void setHaveChildren(boolean haveChildren) {
        this.haveChildren = haveChildren;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Constraint_Employee> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<Constraint_Employee> constraints) {
        this.constraints = constraints;
    }

    public List<com.example.SmartAI.Model.Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<com.example.SmartAI.Model.Shift> shifts) {
        this.shifts = shifts;
    }
}
