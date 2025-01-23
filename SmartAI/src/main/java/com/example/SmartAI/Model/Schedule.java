package com.example.SmartAI.Model;


import javax.persistence.*;
import java.util.List;
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String weekStartDate; // תאריך התחלת השבוע
    private String weekEndDate;   // תאריך סיום השבוע

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<com.example.SmartShiftAI.Model.Shift> shifts; // רשימת המשמרות עבור השבוע

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(String weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public String getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(String weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public List<com.example.SmartShiftAI.Model.Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<com.example.SmartShiftAI.Model.Shift> shifts) {
        this.shifts = shifts;
    }
}
