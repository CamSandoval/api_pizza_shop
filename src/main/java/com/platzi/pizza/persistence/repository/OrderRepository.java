package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.Order.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends ListCrudRepository<OrderEntity,Integer> {
}
