package com.example.SmartAI.Model;
import com.example.SmartAI.Model.Employee;
import com.example.SmartAI.Model.Schedule;
import com.example.SmartAI.Model.ShiftType;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // מזהה ייחודי לכל משמרת
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date; // תאריך המשמרת
    private ShiftType shiftType; // סוג המשמרת (בוקר, צהריים, לילה)
    private int duration; // משך המשמרת בשעות

    @ManyToOne
    @JoinColumn(name= "employeeID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule; // קישור לסידור העבודה

    @ManyToMany
    @JoinTable(
            name = "employee_shift",
            joinColumns = @JoinColumn(name = "shift_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
