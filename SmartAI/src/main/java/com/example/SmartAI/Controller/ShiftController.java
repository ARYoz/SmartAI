package com.example.SmartAI.Controller;


import com.example.SmartAI.Model.Shift;
import com.example.SmartAI.Model.ShiftType;
import com.example.SmartAI.Service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shift")
public class ShiftController {

    @Autowired
    ShiftService shiftService;

    @PostMapping // Add a new Shift
    public ResponseEntity<Shift> addShift(@RequestBody Shift shift)
    {
        List<Shift> existingShifts = shiftService.getALlShifts();
        Shift newShift = shiftService.addShift(shift,existingShifts);
        return ResponseEntity.ok(newShift);
    }
    @GetMapping // Get all the shifts
    public ResponseEntity<List<Shift>> getAllShifts()
    {
        List<Shift> shifts = shiftService.getALlShifts();
        return ResponseEntity.ok(shifts);
    }

    @GetMapping("/{id}") // Get shift by his ID
    public ResponseEntity<Shift> getShiftById(@PathVariable Long id)
    {
        Shift shift = shiftService.getShiftById(id);
        return ResponseEntity.ok(shift);
    }

    @PutMapping // Update the shift
    public ResponseEntity<Shift> updateShift(@PathVariable Long id , @RequestBody Shift UpdateShift)
    {
        Shift shift = shiftService.UpdateShift(id,UpdateShift);
        return ResponseEntity.ok(shift);
    }

    @DeleteMapping // Delete a shift
    public ResponseEntity<String> deleteShift(@PathVariable Long id)
    {
        shiftService.DeleteShift(id);
        return ResponseEntity.ok("Shift with ID:" + id + "deleted successfully");
    }

    @GetMapping("/date/{date}") // Get shifts by thier dates
    public ResponseEntity<List<Shift>> getShiftByDate(@PathVariable String date)
    {
        LocalDate parsedDate = LocalDate.parse(date);
        List<Shift> shifts = shiftService.findByDate(parsedDate);
        return ResponseEntity.ok(shifts);
    }
    @GetMapping("/type/{type}") // Get Shifts by thier type (MORNING/AFTERNON/NIGHT)
    public ResponseEntity<List<Shift>> getShiftByType(@PathVariable ShiftType shiftType)
    {
        List<Shift> shifts = shiftService.findByShiftType(shiftType);
        return ResponseEntity.ok(shifts);
    }





}
