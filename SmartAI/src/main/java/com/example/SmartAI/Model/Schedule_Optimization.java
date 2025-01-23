package com.example.SmartAI.Model;

import org.jetbrains.annotations.Async;

import java.util.ArrayList;
import java.util.List;

public class Schedule_Optimization {

    private List<com.example.SmartAI.Model.Shift> shifts; // כל המשמרות
    private List<Employee> employees; // כל העובדים

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
}
