package com.example.demo.controller;

import com.example.demo.domain.dto.Checkout;
import com.example.demo.domain.dto.RentalAgreement;
import com.example.demo.exception.CheckoutException;
import com.example.demo.service.CheckoutService;
import org.antlr.v4.runtime.misc.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CheckoutController {
    Logger log = LoggerFactory.getLogger(CheckoutController.class);
    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/checkout")
    private ResponseEntity<RentalAgreement> checkout(@NotNull @RequestBody Checkout checkout) {
        try {
            RentalAgreement rentalAgreement = checkoutService.checkOut(checkout);
            rentalAgreement.writeToConsole();
            return new ResponseEntity<>(rentalAgreement,HttpStatus.OK);
        } catch (CheckoutException exception) {
            log.error(exception.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage(), exception);
        }
    }
}
