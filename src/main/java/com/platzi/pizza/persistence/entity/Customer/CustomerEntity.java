package com.platzi.pizza.persistence.entity.Customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platzi.pizza.persistence.entity.Order.OrderEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @Column(name = "id_customer",nullable = false,length = 15)
    private Integer idCustomer;

    @Column(nullable = false,length = 60)
    private String name;
    @Column(length = 100)
    private String address;

    @Column(nullable = false,length = 50,unique = true)
    private String email;

    @Column(name = "phone_number",length = 20)
    private String phoneNumber;

}