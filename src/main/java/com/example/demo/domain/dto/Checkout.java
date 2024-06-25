package com.example.demo.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Checkout {

    private String toolCode;
    private Integer rentalDayCount;
    private Integer discountPercent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yy")
    private LocalDate checkoutDate;

    public Checkout(Checkout checkout) {
        this.toolCode = checkout.getToolCode();
        this.rentalDayCount = checkout.getRentalDayCount();
        this.discountPercent = checkout.getDiscountPercent();
        this.checkoutDate = checkout.getCheckoutDate();
    }

}
