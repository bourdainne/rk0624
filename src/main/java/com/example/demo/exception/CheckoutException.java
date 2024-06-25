package com.example.demo.exception;

import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

public class CheckoutException extends Throwable {
    public enum  EXCEPTION_CODE {
        RENTAL_DAYS("Rental day count - '${value}' is not 1 or greater"),
        DISCOUNT_PCT("Discount percentage - '${value}' is not within the range 0-100"),
        TOOL_CODE_INVALID("Tool code - '${value}' is invalid");

        private final String message;
        public String getMessage() {
            return message;
        }
        EXCEPTION_CODE(String message) {
            this.message = message;
        }
    }
    public EXCEPTION_CODE exceptionCode;
    public CheckoutException(EXCEPTION_CODE exceptionCode, String value) {
        super(StringSubstitutor.replace(exceptionCode.getMessage(), Map.of("value", value)));
        this.exceptionCode = exceptionCode;
    }
}
