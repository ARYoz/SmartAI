package com.example.SmartAI.Service;

import com.example.SmartAI.Model.Employee;
import com.example.SmartAI.Model.Shift;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AIService {

    private final GPTService gptService;
    private final TranslationService translationService;
    private final EmployeeService employeeService;
    private final OptimizationService optimizationService;

    public AIService(GPTService gptService,
                     TranslationService translationService,
                     EmployeeService employeeService,
                     OptimizationService optimizationService) {
        this.gptService = gptService;
        this.translationService = translationService;
        this.employeeService = employeeService;
        this.optimizationService = optimizationService;
    }

    public List<Map<String, Object>> generateScheduleMatrix(String query) {
        // תרגום השאילתה לאנגלית באמצעות GPT
        String translatedQuery = translationService.translateWithGPT(query);

        // בדיקת בקשה לסידור עבודה
        if (translatedQuery.toLowerCase().contains("schedule")) {
            List<Employee> employees = employeeService.getAllEmployees();
            List<Shift> optimizedShifts = optimizationService.generateWeeklyShifts(employees);

            // המרת רשימת המשמרות לפורמט טבלאי
            return convertShiftsToMatrix(optimizedShifts);
        } else {
            throw new IllegalArgumentException("Unsupported query: " + query);
        }
    }

    private List<Map<String, Object>> convertShiftsToMatrix(List<Shift> shifts) {
        Map<String, Map<String, List<String>>> scheduleMatrix = new TreeMap<>(); // ימים -> סוגי משמרת -> רשימת עובדים

        // יצירת מטריצה
        for (Shift shift : shifts) {
            String day = shift.getDate().getDayOfWeek().toString();
            String shiftType = shift.getShiftType().toString().toLowerCase();

            scheduleMatrix.putIfAbsent(day, new TreeMap<>());
            scheduleMatrix.get(day).putIfAbsent(shiftType, new ArrayList<>());

            // הוספת העובד למטריצה
            scheduleMatrix.get(day).get(shiftType).add(shift.getEmployee().getIdNumber() + " (" + shift.getDuration() + ")");
        }

        // המרת המטריצה לרשימה טבלאית בפורמט JSON
        List<Map<String, Object>> table = new ArrayList<>();
        List<String> headerRow = new ArrayList<>(List.of("Shift Type \\ Day"));
        headerRow.addAll(scheduleMatrix.keySet());

        Map<String, Object> headerMap = new LinkedHashMap<>();
        headerMap.put("rowHeader", "Header");
        headerMap.put("columns", headerRow);
        table.add(headerMap);

        for (String shiftType : List.of("morning", "afternoon", "night")) {
            Map<String, Object> rowMap = new LinkedHashMap<>();
            rowMap.put("rowHeader", shiftType);

            for (String day : scheduleMatrix.keySet()) {
                List<String> employees = scheduleMatrix.get(day).getOrDefault(shiftType, new ArrayList<>());
                rowMap.put(day, String.join(", ", employees));
            }

            table.add(rowMap);
        }

        return table;
    }
}
