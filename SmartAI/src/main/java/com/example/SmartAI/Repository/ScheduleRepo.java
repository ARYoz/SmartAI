package com.example.SmartAI.Repository;

import com.example.SmartAI.Model.Schedule;
import com.example.SmartAI.Model.Shift;
import com.example.SmartAI.Model.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {


    // מציאת סידור עבודה שלם עם כל המשמרות וכל העובדים
    @Query(value = "SELECT s.* FROM schedule sc " +
            "JOIN shift s ON sc.id = s.schedule_id " +
            "JOIN employee e ON s.employee_id = e.id " +
            "WHERE sc.id = :scheduleId", nativeQuery = true)
    List<Object[]> findFullScheduleWithShiftsAndEmployees(@Param("scheduleId") Long scheduleId);
}
