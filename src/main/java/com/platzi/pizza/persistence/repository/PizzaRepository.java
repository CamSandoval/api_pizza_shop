package com.platzi.pizza.persistence.repository;


import com.platzi.pizza.persistence.entity.Pizza.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends ListCrudRepository<PizzaEntity,Integer> {
    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);
}
