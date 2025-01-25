package com.example.SmartAI.Service;

import com.example.SmartAI.Model.Constraint_Employee;
import com.example.SmartAI.Model.Employee;
import com.example.SmartAI.Model.Shift;
import com.example.SmartAI.Model.ShiftType;
import com.example.SmartAI.Repository.ConstraintRepo;
import com.example.SmartAI.Repository.EmployeeRepo;
import com.example.SmartAI.Repository.ShiftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OptimizationService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ShiftRepo shiftRepo;

    @Autowired
    private ConstraintRepo constraintRepo;

    public Map<String, Map<String, List<String>>> optimizeSchedule(List<Employee> employees,
                                                                   List<Shift> shifts,
                                                                   List<Constraint_Employee> constraints) {
        // מטריצה לסידור העבודה
        Map<String, Map<String, List<String>>> schedule = initializeSchedule();

        // ניתוח האילוצים לפי דרגת דחיפות
        constraints.sort(Comparator.comparingInt(Constraint_Employee::getPriority).reversed());

        // לולאה על כל המשמרות
        for (Shift shift : shifts) {
            List<String> assignedEmployees = new ArrayList<>();

            // לולאה על העובדים המתאימים
            for (Employee employee : employees) {
                boolean canWork = canEmployeeWork(employee, shift, constraints, schedule);
                if (canWork) {
                    assignedEmployees.add(employee.getIdNumber()  + " (" + shift.getDuration() + ")");
                    if (assignedEmployees.size() >= 5) break; // דרישה למינימום 5 עובדים למשמרת
                }
            }

            // הוספת המשמרת לסידור
            schedule.get(shift.getShiftType()).get(shift.getDate().getDayOfWeek().toString()).addAll(assignedEmployees);
        }

        return schedule;
    }

    // בדיקה אם עובד יכול לעבוד במשמרת מסוימת
    private boolean canEmployeeWork(Employee employee, Shift shift,
                                    List<Constraint_Employee> constraints,
                                    Map<String, Map<String, List<String>>> schedule) {


        // בדיקה אם לעובד יש אילוץ למשמרת זו
        for (Constraint_Employee constraint : constraints) {
            if (constraint.getEmployee().getIdNumber().equals(employee.getIdNumber()) &&
                    constraint.getDate().equals(shift.getDate()) &&
                    constraint.getShiftType().equals(shift.getShiftType())) {
                return false; // העובד לא יכול לעבוד
            }
        }

        String day = shift.getDate().getDayOfWeek().toString().toLowerCase();
        List<String> sameDayShifts = schedule.get(shift.getShiftType()).get(day);
        if (sameDayShifts != null && sameDayShifts.contains(employee.getIdNumber())) {
            return false;
        }

        // בדיקה אם העובד שובץ למשמרת לילה ביום הקודם ושובץ לבוקר
        if (shift.getShiftType().equals("Morning")) {
            LocalDate previousDay = shift.getDate().minusDays(1);
            String previousDayName = previousDay.getDayOfWeek().toString();
            List<String> previousNightShifts = schedule.get("Night").get(previousDayName);
            if (previousNightShifts != null && previousNightShifts.contains(employee.getIdNumber())) {
                return false; // העובד סיים לילה, לא ניתן לשבץ אותו לבוקר
            }
        }
        return true;
    }

    // אתחול המטריצה לסידור העבודה
    private Map<String, Map<String, List<String>>> initializeSchedule() {
        Map<String, Map<String, List<String>>> schedule = new HashMap<>();
        String[] shiftTypes = {"MORNINIG", "AFTERNOON", "NIGHT"};
        String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

        for (String shiftType : shiftTypes) {
            schedule.put(shiftType, new HashMap<>());
            for (String day : days) {
                schedule.get(shiftType).put(day, new ArrayList<>());
            }
        }
        return schedule;
    }

    public List<Shift> generateWeeklyShifts(List<Employee> employees) {
        List<Shift> shifts = new ArrayList<>();

        // דוגמה ללוגיקה בסיסית ליצירת סידור עבודה שבועי
        for (Employee employee : employees) {
            for (int day = 0; day < 7; day++) {
                shifts.add(new Shift(
                        LocalDate.now().plusDays(day),
                        ShiftType.MORNING,
                        employee,
                        8 // מספר שעות לדוגמה
                ));
            }
        }

        return shifts;
    }
}
