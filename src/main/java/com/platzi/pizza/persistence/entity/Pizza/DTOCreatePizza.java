package com.platzi.pizza.persistence.entity.Pizza;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DTOCreatePizza(
        @NotBlank
        String name,
        @NotBlank
        @Pattern(regexp = "^.{10,150}$")
        String description,
        @NotNull(message = "Price must be separated by commas")
        Double price,
        @NotNull
        Boolean vegetarian,
        @NotNull
        Boolean vegan,
        @NotNull Boolean available
) {
}
