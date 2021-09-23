package com.herkoemmlich.decision.domain;

import lombok.Data;

@Data
public class Customer {

    private Long id;
    public int age;
    public String canton;
    public String sex;
}
