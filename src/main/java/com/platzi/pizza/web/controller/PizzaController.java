package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import com.platzi.pizza.service.PizzaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaService service;

    @GetMapping("/all")
    public ResponseEntity<List<PizzaEntity>> pizzaList(){
        return ResponseEntity.ok(service.findAvailable());
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getPizzaDetails(@PathVariable Integer idPizza){
        return ResponseEntity.ok(service.getReferenceById(idPizza));
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<PizzaEntity> createPizza(@RequestBody PizzaEntity pizza, UriComponentsBuilder builder){
        if(pizza.getIdPizza() == null || service.exists(pizza.getIdPizza())){
            PizzaEntity createdPizza = service.createPizza(pizza);
            URI url = builder.path("/pizzas/{idPizza}").buildAndExpand(createdPizza.getIdPizza()).toUri();
            return ResponseEntity.created(url).body(createdPizza);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<PizzaEntity> updatePizza(@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza() != null && service.exists(pizza.getIdPizza())){
            return ResponseEntity.ok(service.createPizza(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{idPizza}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer idPizza){
        if(service.exists(idPizza)){
            PizzaEntity pizza = service.getReferenceById(idPizza);
            pizza.deactivate();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
