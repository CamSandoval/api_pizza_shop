package com.platzi.pizza.persistence.entity.Pizza;

public record DTOPizzaList(Integer idPizza,String name,String description,Double price,Boolean vegetarian,Boolean vegan,Boolean available) {

    public DTOPizzaList(PizzaEntity pizza){
        this(pizza.getIdPizza(),pizza.getName(),pizza.getDescription(),pizza.getPrice(),pizza.getVegetarian(),pizza.getVegan(),pizza.getAvailable());
    }
}
