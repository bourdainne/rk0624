package com.example.demo;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("Test 1")
    public void test1() throws Exception {

        this.mockMvc.perform(
                        post("/checkout")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(createCheckoutJson("JAKR",5,101,"09/03/15"))
                )
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Order(2)
    @DisplayName("Test 2")
    public void test2() throws Exception {

        this.mockMvc.perform(
                        post("/checkout")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(createCheckoutJson("LADW",3,10,"07/02/20"))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("toolCode", equalTo("LADW")))
                .andExpect(jsonPath("toolTypeName", equalTo("Ladder")))
                .andExpect(jsonPath("brandName", equalTo("Werner")))
                .andExpect(jsonPath("rentalDayCount", equalTo(3)))
                .andExpect(jsonPath("checkoutDate", equalTo("07/02/20")))
                .andExpect(jsonPath("dueDate", equalTo("07/05/20")))
                .andExpect(jsonPath("dailyRentalCharge", equalTo(1.99)))
                .andExpect(jsonPath("chargeDays", equalTo(2)))
                .andExpect(jsonPath("preDiscountCharge", equalTo(3.98)))
                .andExpect(jsonPath("discountPercent", equalTo(10)))
                .andExpect(jsonPath("discountAmount", equalTo(0.40)))
                .andExpect(jsonPath("finalCharge", equalTo(3.58)));
    }

    @Test
    @Order(3)
    @DisplayName("Test 3")
    public void test3() throws Exception {

        this.mockMvc.perform(
                        post("/checkout")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(createCheckoutJson("CHNS",5,25,"07/02/15"))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("toolCode", equalTo("CHNS")))
                .andExpect(jsonPath("toolTypeName", equalTo("Chainsaw")))
                .andExpect(jsonPath("brandName", equalTo("Stihl")))
                .andExpect(jsonPath("rentalDayCount", equalTo(5)))
                .andExpect(jsonPath("checkoutDate", equalTo("07/02/15")))
                .andExpect(jsonPath("dueDate", equalTo("07/07/15")))
                .andExpect(jsonPath("dailyRentalCharge", equalTo(1.49)))
                .andExpect(jsonPath("chargeDays", equalTo(3)))
                .andExpect(jsonPath("preDiscountCharge", equalTo(4.47)))
                .andExpect(jsonPath("discountPercent", equalTo(25)))
                .andExpect(jsonPath("discountAmount", equalTo(1.12)))
                .andExpect(jsonPath("finalCharge", equalTo(3.35)));
    }

    @Test
    @Order(4)
    @DisplayName("Test 4")
    public void test4() throws Exception {

        this.mockMvc.perform(
                        post("/checkout")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(createCheckoutJson("JAKD",6,0,"09/03/15"))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("toolCode", equalTo("JAKD")))
                .andExpect(jsonPath("toolTypeName", equalTo("Jackhammer")))
                .andExpect(jsonPath("brandName", equalTo("DeWalt")))
                .andExpect(jsonPath("rentalDayCount", equalTo(6)))
                .andExpect(jsonPath("checkoutDate", equalTo("09/03/15")))
                .andExpect(jsonPath("dueDate", equalTo("09/09/15")))
                .andExpect(jsonPath("dailyRentalCharge", equalTo(2.99)))
                .andExpect(jsonPath("chargeDays", equalTo(3)))
                .andExpect(jsonPath("preDiscountCharge", equalTo(8.97)))
                .andExpect(jsonPath("discountPercent", equalTo(0)))
                .andExpect(jsonPath("discountAmount", equalTo(0.00)))
                .andExpect(jsonPath("finalCharge", equalTo(8.97)));
    }

    @Test
    @Order(5)
    @DisplayName("Test 5")
    public void test5() throws Exception {

        this.mockMvc.perform(
                        post("/checkout")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(createCheckoutJson("JAKR",9,0,"07/02/15"))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("toolCode", equalTo("JAKR")))
                .andExpect(jsonPath("toolTypeName", equalTo("Jackhammer")))
                .andExpect(jsonPath("brandName", equalTo("Ridgid")))
                .andExpect(jsonPath("rentalDayCount", equalTo(9)))
                .andExpect(jsonPath("checkoutDate", equalTo("07/02/15")))
                .andExpect(jsonPath("dueDate", equalTo("07/11/15")))
                .andExpect(jsonPath("dailyRentalCharge", equalTo(2.99)))
                .andExpect(jsonPath("chargeDays", equalTo(6)))
                .andExpect(jsonPath("preDiscountCharge", equalTo(17.94)))
                .andExpect(jsonPath("discountPercent", equalTo(0)))
                .andExpect(jsonPath("discountAmount", equalTo(0.00)))
                .andExpect(jsonPath("finalCharge", equalTo(17.94)));
    }

    @Test
    @Order(6)
    @DisplayName("Test 6")
    public void test6() throws Exception {

        this.mockMvc.perform(
                        post("/checkout")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(createCheckoutJson("JAKR",4,50,"07/02/20"))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("toolCode", equalTo("JAKR")))
                .andExpect(jsonPath("toolTypeName", equalTo("Jackhammer")))
                .andExpect(jsonPath("brandName", equalTo("Ridgid")))
                .andExpect(jsonPath("rentalDayCount", equalTo(4)))
                .andExpect(jsonPath("checkoutDate", equalTo("07/02/20")))
                .andExpect(jsonPath("dueDate", equalTo("07/06/20")))
                .andExpect(jsonPath("dailyRentalCharge", equalTo(2.99)))
                .andExpect(jsonPath("chargeDays", equalTo(1)))
                .andExpect(jsonPath("preDiscountCharge", equalTo(2.99)))
                .andExpect(jsonPath("discountPercent", equalTo(50)))
                .andExpect(jsonPath("discountAmount", equalTo(1.50)))
                .andExpect(jsonPath("finalCharge", equalTo(1.49)));
    }

    private static String createCheckoutJson(String toolCode,int rentalDayCount,int discountPercent,String checkoutDate  ) {
        try {
            JSONObject checkout = new JSONObject();
            checkout.put("toolCode", toolCode);
            checkout.put("rentalDayCount", rentalDayCount);
            checkout.put("discountPercent", discountPercent);
            checkout.put("checkoutDate", checkoutDate);
            return checkout.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}