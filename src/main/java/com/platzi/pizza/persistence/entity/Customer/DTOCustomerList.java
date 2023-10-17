package com.platzi.pizza.persistence.entity.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOCustomerList (
        @NotNull
        Integer idCustomer,
        @NotBlank
        String name,
        @NotBlank
        String address,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phoneNumber
){
}
