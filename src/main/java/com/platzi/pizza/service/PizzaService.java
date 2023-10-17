package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.Pizza.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository repository;
    public Page<PizzaEntity> findAvailable(Pageable pageable){
        return repository.findByAvailableTrue(pageable);
    }

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

}
