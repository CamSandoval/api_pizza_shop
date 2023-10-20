package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.Pizza.DTOCreatePizza;
import com.platzi.pizza.persistence.entity.Pizza.DTOPizzaList;
import com.platzi.pizza.persistence.entity.Pizza.DTOUpdatePizza;
import com.platzi.pizza.persistence.entity.Pizza.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaService service;

    @GetMapping("/all")
    public ResponseEntity<Page<DTOPizzaList>> pizzaList(@PageableDefault(size = 5)Pageable pageable){
        return ResponseEntity.ok(service.findAvailable(pageable).map(DTOPizzaList::new));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<DTOPizzaList> getPizzaDetails(@PathVariable Integer idPizza){
        PizzaEntity pizza = service.getReferenceById(idPizza);
        return ResponseEntity.ok(new DTOPizzaList(pizza));
    }

    @GetMapping("/name/{pizzaName}")
    public ResponseEntity<DTOPizzaList> getByName(@PathVariable String pizzaName){
        PizzaEntity pizza = service.getPizzaByName(pizzaName);
        return ResponseEntity.ok(new DTOPizzaList(pizza));
    }

    @GetMapping("/with/{description}")
    public ResponseEntity<Page<DTOPizzaList>> pizzasFilterByIngredient(@PageableDefault(size = 2)Pageable pageable,@PathVariable String description){
        return ResponseEntity.ok(service.getPizzaWithFilterByIngredient(pageable,description).map(DTOPizzaList::new));
    }
    @GetMapping("/withOut/{description}")
    public ResponseEntity<Page<DTOPizzaList>> pizzasWithOutIngredient(@PageableDefault(size = 2)Pageable pageable,@PathVariable String description){
        return ResponseEntity.ok(service.getPizzaWithOutIngredient(pageable,description).map(DTOPizzaList::new));
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<DTOPizzaList> createPizza(@Valid @RequestBody DTOCreatePizza data, UriComponentsBuilder builder){
        PizzaEntity pizza = service.createPizza(new PizzaEntity(data));
        if(pizza.getIdPizza() == null || service.exists(pizza.getIdPizza())){
            DTOPizzaList createdPizza =new DTOPizzaList(pizza);
            URI url = builder.path("/pizzas/{idPizza}").buildAndExpand(pizza.getIdPizza()).toUri();
            return ResponseEntity.created(url).body(createdPizza);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DTOPizzaList> updatePizza(@RequestBody DTOUpdatePizza data){
        PizzaEntity pizza = service.getReferenceById(data.idPizza());
        if(pizza.getIdPizza() != null && service.exists(pizza.getIdPizza())){
            pizza.update(data);
            return ResponseEntity.ok(new DTOPizzaList(pizza));
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
