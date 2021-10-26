package com.herkoemmlich.decision.controller;

import java.util.Base64;

import com.herkoemmlich.decision.RiskCalculationService;
import com.herkoemmlich.decision.domain.Customer;
import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.util.Strings;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {

    @Autowired
    RiskCalculationService riskCalculationService;

    @PostMapping(value = "/", produces = "text/plain", consumes = "application/json")
    public String calculateRisk(@RequestBody Customer customer,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        logToken(authHeader);
        log.info("Calculating risk of customer with id={}", customer.getId());

        return String.valueOf(riskCalculationService.calculateRisk(customer));
    }

    private void logToken(String authHeader) {
        if (Strings.isEmpty(authHeader)) return;
        
        try {
            String[] chunks = authHeader.substring(7).split("\\.");
            JSONObject json = (JSONObject) new JSONParser().parse(
                    new String(Base64.getDecoder().decode(chunks[1])));

            String subject = (String) json.get("upn");
            String client = (String) json.get("appid");
            String audience = (String) json.get("aud");
            log.info("Got token with subject={} and client={} for audience={}",
                    subject, client, audience);
        } catch (Exception e) {
            log.error("Cannot parse JWT");
        }
    }

}
