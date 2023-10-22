package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.Order.OrderEntity;
import com.platzi.pizza.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;

    private static final String DELIVERY= "D";
    private static final String CARRYOUT= "C";
    private static final String ONSITE= "S";

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.repository=orderRepository;
    }

    public List<OrderEntity> getAll(){
        return repository.findAll();
    }

    /**
     *This method sends a pageable information and today's date to repository
     * @return page with today's orders */
    public Page<OrderEntity> getTodayOrders(Pageable pageable){
        LocalDateTime today= LocalDate.now().atTime(0,0);
        return repository.findAllByDateAfter(today,pageable);
    }

    /**
     *This method sends a pageable information and list of characters of delivery and carryout orders
     * @return a page with delivery and carryout OrderEntities */
    public Page<OrderEntity> getOutsideOrders(Pageable pageable){
        List<String> methods= Arrays.asList(DELIVERY,CARRYOUT);
        return repository.findAllByMethodIn(methods,pageable);
    }
}
