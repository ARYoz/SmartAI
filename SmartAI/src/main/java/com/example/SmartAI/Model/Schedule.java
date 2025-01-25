package com.example.SmartAI.Model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate weekStartDate; // תאריך התחלת השבוע
    private LocalDate weekEndDate;   // תאריך סיום השבוע

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<com.example.SmartAI.Model.Shift> shifts; // רשימת המשמרות עבור השבוע

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(LocalDate weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public LocalDate getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(LocalDate weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public List<com.example.SmartAI.Model.Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<com.example.SmartAI.Model.Shift> shifts) {
        this.shifts = shifts;
    }
    public void addShift(Shift shift) {
        if (shift != null) {
            shift.setSchedule(this); // Set the relationship between Shift and Schedule
            this.shifts.add(shift);
        }
    }

    // Removing a shift from the schedule
    public void removeShift(Shift shift) {
        if (shift != null) {
            this.shifts.remove(shift);
            shift.setSchedule(null); // Break the relationship
        }
    }

}
