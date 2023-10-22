package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.Pizza.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaPagSortRepository;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    private final PizzaRepository repository;
    private final PizzaPagSortRepository sortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository,PizzaPagSortRepository pizzaPagSortRepository){
        this.repository=pizzaRepository;
        this.sortRepository=pizzaPagSortRepository;
    }
    public Page<PizzaEntity> findAvailable(int page,int elements,String sortBy,String sortDirection){
        Sort sort =Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pagRequest = PageRequest.of(page, elements, sort);
        return sortRepository.findByAvailableTrue(pagRequest);
    }

    public PizzaEntity getReferenceById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public PizzaEntity getPizzaByName(String name){
        return repository.findTopByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("Pizza doesn't exist"));
    }
    public Page<PizzaEntity> getPizzaWithFilterByIngredient(Pageable pageable ,String description){
        return repository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(pageable, description);
    }
    public Page<PizzaEntity> getPizzaWithOutIngredient(Pageable pageable ,String description){
        return repository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(pageable, description);
    }
    public Page<PizzaEntity> getCheapest(double price, Pageable pageable){
        return repository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price,pageable);
    }

    public PizzaEntity createPizza(PizzaEntity pizza){
        return repository.save(pizza);
    }
    public boolean exists(Integer idPizza){
        return repository.existsById(idPizza);
    }

}
