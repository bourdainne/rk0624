package com.example.demo.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RentalAgreement extends Checkout {

    private String toolTypeName;
    private String brandName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yy")
    private LocalDate dueDate;
    private BigDecimal dailyRentalCharge;
    private Long chargeDays;
    private BigDecimal preDiscountCharge;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public RentalAgreement(Checkout checkout) {
        super(checkout);
    }

    public void writeToConsole(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        NumberFormat usd = NumberFormat.getCurrencyInstance(Locale.US);

        System.out.println( "Tool code: " + this.getToolCode());
        System.out.println( "Tool type: " + this.getToolTypeName());
        System.out.println( "Tool brand: " + this.getBrandName());
        System.out.println( "Rental days: " + this.getRentalDayCount());
        System.out.println( "Check out date: " + this.getCheckoutDate().format(formatter));
        System.out.println( "Due date: " + this.getDueDate().format(formatter));
        System.out.println( "Daily rental charge: " + usd.format(this.getDailyRentalCharge()));
        System.out.println( "Charge days: " + this.getChargeDays());
        System.out.println( "Pre-discount charge: " + usd.format(this.getPreDiscountCharge()));
        System.out.println( "Discount percent: " + this.getDiscountPercent()+"%");
        System.out.println( "Discount amount: " + usd.format(this.getDiscountAmount()));
        System.out.println( "Final charge: " + usd.format(this.getFinalCharge()));

    }

}
