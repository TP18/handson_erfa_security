package com.herkoemmlich.bff.controller;

import com.herkoemmlich.bff.domain.Customer;
import com.herkoemmlich.bff.repository.DbServiceRepository;
import com.herkoemmlich.bff.repository.DecisionServiceRepository;
import com.herkoemmlich.bff.controller.exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class Controller {

    @Autowired
    DbServiceRepository dbServiceRepository;

    @Autowired
    DecisionServiceRepository decisionServiceRepository;

    @GetMapping(value = "/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(path = "/", consumes = "text/plain", produces = "application/json")
    public CalculateResponse calculateRisk(@RequestBody String customerId) {
        log.info("Calculating sterberisiko of customer with id={}", customerId);

        Customer customer = dbServiceRepository.getCustomer(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        log.info("customer received = {}", customer);

        float calculatedRisk = Float.parseFloat(decisionServiceRepository.calculateRisk(customer));
        log.info("calculated risk={} for customer with id={}", calculatedRisk, customerId);

        return new CalculateResponse(customer, calculatedRisk);
    }

    public static class CalculateResponse {
        private Customer customer;
        private float risk;

        public CalculateResponse(Customer customer, float risk) {
            this.customer = customer;
            this.risk = risk;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public float getRisk() {
            return risk;
        }

        public void setRisk(float risk) {
            this.risk = risk;
        }
    }
}
