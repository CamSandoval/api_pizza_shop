package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository repository;

    public List<PizzaEntity> getAll(){
        return repository.findAll();
    }

    public PizzaEntity getReferenceById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public PizzaEntity createPizza(PizzaEntity pizza){
        return repository.save(pizza);
    }
    public boolean exists(Integer idPizza){
        return repository.existsById(idPizza);
    }

    public List<PizzaEntity> findAvailable(){
        return repository.findByAvailableTrue();
    }

}
