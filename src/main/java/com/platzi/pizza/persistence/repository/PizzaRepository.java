package com.platzi.pizza.persistence.repository;


import com.platzi.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends ListCrudRepository<PizzaEntity,Integer> {
    List<PizzaEntity> findByAvailableTrue();
}
