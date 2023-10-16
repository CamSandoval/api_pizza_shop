package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends ListCrudRepository<OrderEntity,Integer> {
}
