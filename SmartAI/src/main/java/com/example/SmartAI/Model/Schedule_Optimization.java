package com.example.SmartAI.Model;

import org.jetbrains.annotations.Async;

import java.util.ArrayList;
import java.util.List;

public class Schedule_Optimization {

    private List<com.example.SmartAI.Model.Shift> shifts; // כל המשמרות
    private List<Employee> employees; // כל העובדים
    private List<Constraint_Employee> constraintEmployees; //רשימה של כל האילוצים

    public Schedule_Optimization(List<com.example.SmartAI.Model.Shift> shifts, List<Employee> employees) {
        this.shifts = shifts;
        this.employees = employees;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Constraint_Employee> getConstraintEmployees() {
        return constraintEmployees;
    }

    public void setConstraintEmployees(List<Constraint_Employee> constraintEmployees) {
        this.constraintEmployees = constraintEmployees;
    }
}
