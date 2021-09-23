package com.herkoemmlich.bff.repository;

import com.herkoemmlich.bff.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${db.name}", url = "${db.url}")
public interface DbServiceRepository {

    @RequestMapping(method = RequestMethod.GET, value = "/customer/{id}", consumes = "applicaton/json")
    Customer getCustomer(@PathVariable(value = "id") String customerId);
}
