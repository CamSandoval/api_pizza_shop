package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.Customer.CustomerEntity;
import com.platzi.pizza.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.repository =customerRepository;
    }

    public CustomerEntity findByPhone(String phone){
        return repository.findByPhone(phone);
    }
}
