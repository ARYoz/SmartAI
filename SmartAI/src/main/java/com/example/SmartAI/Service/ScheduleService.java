package com.example.SmartAI.Service;

import com.example.SmartAI.Model.Schedule;
import com.example.SmartAI.Model.Shift;
import com.example.SmartAI.Model.ShiftType;
import com.example.SmartAI.Repository.ScheduleRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleService {

    private  ScheduleRepo scheduleRepo;

    public ScheduleService(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    @Transactional
    public Schedule createWeeklySchedule(LocalDate startDate, LocalDate endDate, List<Shift> shifts) { //POST
        Schedule schedule = new Schedule();
        schedule.setWeekStartDate(startDate);
        schedule.setWeekEndDate(endDate);
        schedule.setShifts(shifts);

        // קישור המשמרות לסידור
        for (Shift shift : shifts) {
            shift.setSchedule(schedule);
        }

        return scheduleRepo.save(schedule);
    }

    @Transactional
    public void updateShiftInSchedule(Long scheduleId, Long shiftId, ShiftType newShiftType) { //UPDATE
        Schedule schedule = scheduleRepo.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        for (Shift shift : schedule.getShifts()) {
            if (shift.getId().equals(shiftId)) {
                shift.setShiftType(newShiftType);
                break;
            }
        }

        scheduleRepo.save(schedule);
    }
    public List<Map<String, Object>> getFullScheduleWithDetails(Long scheduleId) { //GAT the schedule with all the shifts and the employees
        List<Object[]> results = scheduleRepo.findFullScheduleWithShiftsAndEmployees(scheduleId);

        // Parse the results into a more readable format
        List<Map<String, Object>> formattedResults = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> scheduleDetail = new HashMap<>();
            scheduleDetail.put("shiftId", row[0]);
            scheduleDetail.put("employeeName", row[1]); // שם העובד
            scheduleDetail.put("date", row[2]); // תאריך המשמרת
            scheduleDetail.put("shiftType", row[3]); // סוג המשמרת
            formattedResults.add(scheduleDetail); // הוספה לרשימה המעובדת
        }
        return formattedResults;
    }

}
