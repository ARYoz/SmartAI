package com.example.SmartAI.Service;



import com.example.SmartAI.Model.Employee;
import com.example.SmartAI.Model.Shift;
import com.example.SmartAI.Model.ShiftType;
import com.example.SmartAI.Repository.ShiftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {
    @Autowired
    private ShiftRepo shiftRepo;


    public ShiftService(ShiftRepo shiftRepo)
    {
        this.shiftRepo = shiftRepo;
    }

    public Shift addShift(Shift shift, List<Shift> existingShifts) {
        // בדיקה אם לעובד יש משמרת באותו יום
        boolean hasShiftSameDay = existingShifts.stream()
                .anyMatch(existingShift ->
                        existingShift.getEmployee().getIdNumber().equals(shift.getEmployee().getIdNumber()) &&
                                existingShift.getDate().equals(shift.getDate()));
        if (hasShiftSameDay) {
            throw new RuntimeException("This employee already has a shift on this date.");
        }

        // בדיקה אם לעובד יש משמרת לילה ביום הקודם
        boolean hasNightBefore = existingShifts.stream()
                .anyMatch(existingShift ->
                        existingShift.getEmployee().getIdNumber().equals(shift.getEmployee().getIdNumber()) &&
                                existingShift.getDate().equals(shift.getDate().minusDays(1)) &&
                                existingShift.getShiftType() == ShiftType.NIGHT
                );
        if (hasNightBefore && shift.getShiftType() == ShiftType.MORNING) {
            throw new RuntimeException("Employee cannot work a morning shift after a night shift");
        }

        // בדיקה אם המשמרת חורגת מהגבלת השעות
        if (shift.getDuration() > 12) {
            throw new RuntimeException("Shift duration cannot exceed 12 hours.");
        }

        // החזרת המשמרת לאחר אימות
        return shift;
    }

    public List<Shift> getALlShifts() // GET
    {
        return shiftRepo.findAll();
    }
    public Shift getShiftById(Long id)
    {
        return shiftRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Shift not found with id" + id));
    }

    public Shift UpdateShift(Long id, Shift shiftDetails)
    {
        Shift existsShift = shiftRepo.getById(id);

        if(existsShift == null)
        {
            throw new RuntimeException("This shift not fount with this Id:" + id);
        }
        existsShift.setShiftType(shiftDetails.getShiftType());
        existsShift.setDate(shiftDetails.getDate());
        existsShift.setDuration(shiftDetails.getDuration());

        return shiftRepo.save(existsShift);
    }

    public void  DeleteShift(Long id) // Delte Shift
    {
        Shift existsShift = getShiftById(id);
        shiftRepo.delete(existsShift);
    }

    public List<Shift> findByShiftType(ShiftType shiftType)
    {
        return shiftRepo.findByShiftType(shiftType);
    }

    public List<Shift> findByDate(LocalDate date)
    {
        return shiftRepo.findByDate(date);
    }
}
