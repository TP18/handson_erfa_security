package com.herkoemmlich.db.controller;

import java.util.Base64;

import com.herkoemmlich.db.domain.Customer;
import com.herkoemmlich.db.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/customer/{id}", produces = "application/json")
    public Customer findById(@PathVariable("id") long id,
            @RequestHeader("Authorization") String authHeader) {
        logToken(authHeader);
        log.info("Loading customer with id={}", id);

        return customerRepository.findById(id).orElse(null);
    }

//************************ Start Security *******************

private void logToken(String authHeader) {
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

//************************ End Security *******************

}
