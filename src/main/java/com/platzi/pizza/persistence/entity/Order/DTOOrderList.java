package com.platzi.pizza.persistence.entity.Order;

import com.platzi.pizza.persistence.entity.Customer.CustomerEntity;

import java.time.LocalDateTime;
import java.util.List;

public record DTOOrderList(Integer idOrder, Integer idCustomer, LocalDateTime date, Double total, String additionalNotes,
                           CustomerEntity customer,List<OrderItemEntity> items) {
    public DTOOrderList(OrderEntity order){
        this(order.getIdOrder(), order.getIdCustomer(), order.getDate(), order.getTotal(), order.getAdditionalNotes(), order.getCustomer(),order.getOrderItems());
    }
}
