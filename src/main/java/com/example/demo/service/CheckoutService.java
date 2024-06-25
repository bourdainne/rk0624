package com.example.demo.service;

import com.example.demo.domain.dto.Checkout;
import com.example.demo.domain.dto.RentalAgreement;
import com.example.demo.exception.CheckoutException;

public interface CheckoutService {
    RentalAgreement checkOut(Checkout checkout) throws CheckoutException;
}