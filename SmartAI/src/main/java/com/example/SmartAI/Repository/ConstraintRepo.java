package com.example.SmartAI.Repository;

import com.example.SmartAI.Model.Constraint_Employee;
import com.example.SmartAI.Model.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConstraintRepo extends JpaRepository<Constraint_Employee, Long> {

        @Query(value = "SELECT * FROM constraint_employee WHERE date = :date", nativeQuery = true)
        List<Constraint_Employee> findAllByDate(@Param("date") LocalDate date);

        @Query(value = "SELECT * FROM constraint_employee WHERE shift_type = :shiftType", nativeQuery = true)
        List<Constraint_Employee> findAllByShiftType(@Param("shiftType") ShiftType shiftType);

        @Query(value = "SELECT * FROM constraint_employee WHERE date = :date AND shift_type = :shiftType", nativeQuery = true)
        List<Constraint_Employee> findAllByDateAndShiftType(@Param("date") LocalDate date, @Param("shiftType") ShiftType shiftType);

        boolean existsByEmployeeIDAndDateAndShiftType(Long employeeID, LocalDate date, ShiftType shiftType);

        // ספירת אילוצים לשבוע
        long countByEmployeeIDAndDateBetween(Long employeeID, LocalDate startDate, LocalDate endDate);

}