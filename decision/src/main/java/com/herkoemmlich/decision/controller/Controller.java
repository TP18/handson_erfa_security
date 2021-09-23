package com.herkoemmlich.decision.controller;

import com.herkoemmlich.decision.RiskCalculationService;
import com.herkoemmlich.decision.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {

    @Autowired
    RiskCalculationService riskCalculationService;

    @PostMapping(value = "/", produces = "text/plain", consumes = "application/json")
    public String calculateRisk(@RequestBody Customer customer) {
        log.info("Calculating risk of customer with id={}", customer.getId());

        return String.valueOf(riskCalculationService.calculateRisk(customer));
    }

}
