package com.platzi.pizza.persistence.entity.Customer;

public record DTOCustomerList (Integer idCustomer, String name, String address, String email, String phoneNumber){
        public DTOCustomerList(CustomerEntity entity){
                this(entity.getIdCustomer(), entity.getName(), entity.getAddress(), entity.getEmail(), entity.getPhoneNumber());
        }
}
