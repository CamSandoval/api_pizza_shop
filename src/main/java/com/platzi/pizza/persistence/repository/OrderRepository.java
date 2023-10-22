package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.Order.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository  extends ListCrudRepository<OrderEntity,Integer> {
    Page<OrderEntity> findAllByDateAfter(LocalDateTime dateTime, Pageable pageable);
    Page<OrderEntity> findAllByMethodIn(List<String> methods,Pageable pageable);
}
