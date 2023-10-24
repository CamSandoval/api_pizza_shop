package com.platzi.pizza.persistence.entity.Pizza;

import lombok.Data;
import lombok.Getter;

//@Data allows to create automatically the getters and setters, and constructors of the class
@Data
@Getter
public class UpdatePizzaPriceDTO {
    private int pizzaId;
    private double newPrice;
}
