package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.Order.DTOOrderList;
import com.platzi.pizza.persistence.entity.Order.OrderEntity;
import com.platzi.pizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController (OrderService orderService){
        this.service=orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<Stream<DTOOrderList>> getAll(){
        return ResponseEntity.ok(service.getAll().stream().map(DTOOrderList::new));
    }
}
