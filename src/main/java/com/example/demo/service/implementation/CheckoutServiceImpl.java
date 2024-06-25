package com.example.demo.service.implementation;

import com.example.demo.domain.dto.Checkout;
import com.example.demo.domain.dto.RentalAgreement;
import com.example.demo.domain.entity.ToolEntity;
import com.example.demo.exception.CheckoutException;
import com.example.demo.repository.ToolRepository;
import com.example.demo.service.CheckoutService;
import com.example.demo.service.HolidayService;
import com.example.demo.service.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    ToolRepository toolRepository;
    @Autowired
    HolidayService holidayService;
    @Override
    public RentalAgreement checkOut(Checkout checkout) throws CheckoutException {
        RentalAgreement rentalAgreement = new RentalAgreement(checkout);

        if(rentalAgreement.getRentalDayCount() < 1) {
            throw new CheckoutException(CheckoutException.EXCEPTION_CODE.RENTAL_DAYS, rentalAgreement.getRentalDayCount().toString());
        }
        if(rentalAgreement.getDiscountPercent() > 100 || rentalAgreement.getDiscountPercent() < 0) {
            throw new CheckoutException(CheckoutException.EXCEPTION_CODE.DISCOUNT_PCT, rentalAgreement.getDiscountPercent().toString());
        }

        ToolEntity  tool = toolRepository.findByToolCode(checkout.getToolCode()).orElse(null);
        if(Objects.isNull(tool)){
            throw new CheckoutException(CheckoutException.EXCEPTION_CODE.TOOL_CODE_INVALID, rentalAgreement.getToolCode());
        }

        rentalAgreement.setToolTypeName(tool.getToolDetails().getToolType());
        rentalAgreement.setBrandName(tool.getBrand().getName());
        LocalDate checkoutEndDate = checkout.getCheckoutDate().plusDays(checkout.getRentalDayCount());
        rentalAgreement.setDueDate(checkoutEndDate);
        rentalAgreement.setDailyRentalCharge(tool.getToolDetails().getDailyCharge());

        List<LocalDate> holidayDates = holidayService.getHolidaysPerYear(Utils.getYearsForDateRange(checkout.getCheckoutDate(),checkoutEndDate));

        long chargableDays = checkout.getRentalDayCount();
        if(!tool.getToolDetails().getWeekdayCharge()) {
            chargableDays -= Utils.getWeekdayDays(checkout.getCheckoutDate(),checkoutEndDate).size();
        }
        if(!tool.getToolDetails().getWeekendCharge()){
            chargableDays -= Utils.getWeekendDays(checkout.getCheckoutDate(),checkoutEndDate).size();
        }
        if(!tool.getToolDetails().getHolidayCharge()){
            chargableDays -= Utils.getHolidayDays(checkout.getCheckoutDate(),checkoutEndDate,holidayDates).size();
        }

        rentalAgreement.setChargeDays(chargableDays);

        BigDecimal preDiscountCharge = new BigDecimal(chargableDays);
        BigDecimal totalDiscountAmount;
        BigDecimal finalChargeAmount;
        BigDecimal discountPercent = new BigDecimal(checkout.getDiscountPercent()).divide(BigDecimal.valueOf(100), 2, RoundingMode.UNNECESSARY);

        preDiscountCharge = preDiscountCharge.multiply(tool.getToolDetails().getDailyCharge()).setScale(2, RoundingMode.HALF_UP);
        totalDiscountAmount = preDiscountCharge.multiply(discountPercent).setScale(2,RoundingMode.HALF_UP);
        finalChargeAmount = preDiscountCharge.subtract(totalDiscountAmount);

        rentalAgreement.setPreDiscountCharge(preDiscountCharge);
        rentalAgreement.setDiscountAmount(totalDiscountAmount);
        rentalAgreement.setFinalCharge(finalChargeAmount);

        return rentalAgreement;
    }
}
