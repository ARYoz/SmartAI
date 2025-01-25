package com.example.SmartAI.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Constraint_Employee {

    @Id @GeneratedValue
    private Long employeeID;

    private String reason;
    private LocalDate date;
    private ShiftType shiftType;
    private int priority;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ShiftType getShiftType() {
        return shiftType;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (this.priority > 10) {
            this.priority = 10;
        } else if (this.priority < 0) {
            this.priority = 0;
        } else {
            this.priority = priority;
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
