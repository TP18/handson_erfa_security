package com.herkoemmlich.db.controller;

import com.herkoemmlich.db.domain.Customer;
import com.herkoemmlich.db.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/customer/{id}", produces = "application/json")
    public Customer findById(@PathVariable("id") long id) {
        log.info("Loading customer with id={}", id);
        return customerRepository.findById(id).orElse(null);
    }

}
