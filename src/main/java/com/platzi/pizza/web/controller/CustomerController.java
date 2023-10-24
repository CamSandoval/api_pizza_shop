package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.Customer.CustomerEntity;
import com.platzi.pizza.persistence.entity.Customer.DTOCustomerList;
import com.platzi.pizza.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.service=customerService;
    }

    @GetMapping("/{phone}")
    public ResponseEntity<DTOCustomerList> getByPhone(@PathVariable String phone){
        CustomerEntity customer = service.findByPhone(phone);
        return ResponseEntity.ok(new DTOCustomerList(customer)) ;
    }
}
