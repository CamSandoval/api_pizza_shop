package com.platzi.pizza.persistence.entity.Pizza;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza")
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity {
    @Id
    @Column(name = "id_pizza",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPizza;

    @Column(nullable = false,length = 30,unique = true)
    private String name;

    @Column(nullable = false,length = 150)
    private String description;

    @Column(nullable = false,columnDefinition = "Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;

    @Column(columnDefinition = "TINYINT",nullable = false)
    private Boolean available;

    public void deactivate() {
        this.available=false;
    }

    public PizzaEntity(DTOCreatePizza data){
        this.name= data.name();
        this.description= data.description();
        this.price= data.price();
        this.vegetarian= data.vegetarian();
        this.vegan= data.vegan();
        this.available= data.available();
    }

    public void update(DTOUpdatePizza data) {
        this.name= data.name();
        this.description= data.description();
        this.price= data.price();
        this.vegetarian= data.vegetarian();
        this.vegan= data.vegan();
        this.available= data.available();
    }
}
