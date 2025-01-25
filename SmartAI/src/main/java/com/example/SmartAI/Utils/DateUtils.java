package com.example.SmartAI.Utils;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {

    // חישוב תחילת השבוע
    public static LocalDate getStartOfWeek(LocalDate date) {
        return date.with(DayOfWeek.SUNDAY); // תחילת השבוע - יום ראשון
    }

    // חישוב סוף השבוע
    public static LocalDate getEndOfWeek(LocalDate date) {
        return date.with(DayOfWeek.SATURDAY); // סוף השבוע - יום שבת
    }
}


