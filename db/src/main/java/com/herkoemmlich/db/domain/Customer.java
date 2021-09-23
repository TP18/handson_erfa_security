package com.herkoemmlich.db.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Customer {
    @Id
    private Long id;
    public int age;
    public String canton;
    public String sex;
}
