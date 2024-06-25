package com.example.demo;

import com.example.demo.service.HolidayService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HolidaysTest {

    @Autowired
    private HolidayService holidayService;

    @Test
    @Order(1)
    @DisplayName("Test observed Independence and Labor day holidays for years 2024 thru 2027")
    public void testHolidays() {

        List<LocalDate> independenceDayObserved = new ArrayList<>();
        List<LocalDate> laborDayObserved = new ArrayList<>();
        Arrays.asList(2024, 2025, 2026, 2027).forEach(
                i->{
                    switch (i) {
                        case 2024:
                            independenceDayObserved.add(LocalDate.of(2024,7,4));
                            laborDayObserved.add(LocalDate.of(2024,9,2));
                            break;
                        case 2025:
                            independenceDayObserved.add(LocalDate.of(2025,7,4));
                            laborDayObserved.add(LocalDate.of(2025,9,1));
                            break;
                        case 2026:
                            independenceDayObserved.add(LocalDate.of(2026,7,3));
                            laborDayObserved.add(LocalDate.of(2026,9,7));
                            break;
                        case 2027:
                            independenceDayObserved.add(LocalDate.of(2027,7,5));
                            laborDayObserved.add(LocalDate.of(2027,9,6));
                            break;
                        default: break;
                    }
                }
        );

        List<LocalDate> holidays = holidayService.getHolidaysPerYear(Arrays.asList(2024, 2025, 2026, 2027));
        holidays.removeAll(independenceDayObserved);
        holidays.removeAll(laborDayObserved);
        assertTrue(holidays.isEmpty());
    }

}
