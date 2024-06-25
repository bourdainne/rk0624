package com.example.demo.service.implementation;

import com.example.demo.controller.CheckoutController;
import com.example.demo.domain.entity.HolidayEntity;
import com.example.demo.repository.HolidayRepository;
import com.example.demo.service.HolidayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class HolidayServiceImpl implements HolidayService {

    Logger log = LoggerFactory.getLogger(HolidayServiceImpl.class);

    @Autowired
    HolidayRepository holidayRepository;

    @Override
    public List<LocalDate> getHolidaysPerYear(List<Integer> years) {

        List<HolidayEntity>  holidayEntities = holidayRepository.findAll();

        List<LocalDate> holidays = years
            .stream()
            .flatMap(
                    year -> holidayEntities
                            .stream()
                            .map(
                                holiday -> {
                                    return holiday.getIsFixed()?getFixedHolidayDate(year,holiday):getMoveableHolidayDate(year,holiday);
                            })
            ).collect(Collectors.toList());

        return holidays;
    }

    private LocalDate getFixedHolidayDate(Integer year , HolidayEntity holidayEntity){
        LocalDate holiday = LocalDate.of(year, Month.of(holidayEntity.getMonthOfYear()), holidayEntity.getDayOfMonth());
        switch(holiday.getDayOfWeek().getValue())
        {
            case 1 :
            case 2 :
            case 3 :
            case 4 :
            case 5 : return holiday;
            case 6 : return holiday.minusDays(1);
            default : return holiday.plusDays(1);
        }
    }

    private LocalDate getMoveableHolidayDate(Integer year , HolidayEntity holidayEntity){
        LocalDate firstPossibleDate = LocalDate.of(year, Month.of(holidayEntity.getMonthOfYear()), holidayEntity.getEarliestDay());
        LocalDate lastPossibleDate = LocalDate.of(year, Month.of(holidayEntity.getMonthOfYear()), holidayEntity.getLatestDay());
        while(!firstPossibleDate.isAfter(lastPossibleDate)){
            if(firstPossibleDate.getDayOfWeek() == DayOfWeek.of(holidayEntity.getDayOfWeek())){
                break;
            }
            firstPossibleDate = firstPossibleDate.plusDays(1);
        }
        return firstPossibleDate;
    }

}
