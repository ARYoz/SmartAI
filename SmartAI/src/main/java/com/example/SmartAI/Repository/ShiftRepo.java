package com.example.SmartAI.Repository;


import com.example.SmartAI.Model.Shift;
import com.example.SmartAI.Model.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShiftRepo extends JpaRepository<Shift, Long> {

    // חיפוש משמרות לפי סוג (בוקר/צהריים/לילה)
    List<Shift> findByShiftType(ShiftType shiftType);

    List<Shift> findByDate(LocalDate date); // חיפוש לפי תאריך



}