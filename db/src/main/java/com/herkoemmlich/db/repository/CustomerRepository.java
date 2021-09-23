package com.herkoemmlich.db.repository;

import com.herkoemmlich.db.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAll();

    Optional<Customer> findById(Long aLong);
}
