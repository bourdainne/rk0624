package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

public interface HolidayService {
    List<LocalDate> getHolidaysPerYear(List<Integer> years);

}
