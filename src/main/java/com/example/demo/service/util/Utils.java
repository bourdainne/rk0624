package com.example.demo.service.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Utils {

    public static Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
            || date.getDayOfWeek() == DayOfWeek.SUNDAY;

    public static List<LocalDate> getWeekendDays(LocalDate startDate, LocalDate endDate) {

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        return Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(daysBetween)
                .filter(isWeekend)
                .collect(Collectors.toList());
    }
    public static List<LocalDate> getWeekdayDays(LocalDate startDate, LocalDate endDate) {

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        return Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(daysBetween)
                .filter(isWeekend.negate())
                .collect(Collectors.toList());
    }
    public static List<LocalDate> getHolidayDays(LocalDate startDate, LocalDate endDate,List<LocalDate> holidaysPerYear) {

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        return Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(daysBetween)
                .filter(holidaysPerYear::contains)
                .collect(Collectors.toList());
    }
    public static List<Integer> getYearsForDateRange(LocalDate startDate, LocalDate endDate) {
        return IntStream.rangeClosed(startDate.getYear(), endDate.getYear())
                .map(i -> i++)
                .boxed()
                .collect(Collectors.toList());
    }

}
