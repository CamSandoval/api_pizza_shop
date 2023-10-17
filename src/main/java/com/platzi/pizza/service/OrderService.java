package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.Order.OrderEntity;
import com.platzi.pizza.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.repository=orderRepository;
    }

    public List<OrderEntity> getAll(){
        return repository.findAll();
    }
}
