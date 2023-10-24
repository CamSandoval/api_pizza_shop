package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.Order.DTOOrderList;
import com.platzi.pizza.persistence.projection.OrderSummary;
import com.platzi.pizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Stream;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController (OrderService orderService){
        this.service=orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<Stream<DTOOrderList>> getAll(){
        return ResponseEntity.ok(service.getAll().stream().map(DTOOrderList::new));
    }

    @GetMapping("/today")
    public ResponseEntity<Page<DTOOrderList>> getTodayOrders(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok((service.getTodayOrders(pageable).map(DTOOrderList ::new)));
    }

    @GetMapping("/outside")
    public ResponseEntity<Page<DTOOrderList>> getOutsideOrders(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(service.getOutsideOrders(pageable).map(DTOOrderList ::new));
    }

    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<Page<DTOOrderList>> getCustomerOrders(@PathVariable String idCustomer,@PageableDefault(size=2)Pageable pageable){
        return ResponseEntity.ok(service.getCustomerOrder(idCustomer,pageable).map(DTOOrderList::new));
    }

    @GetMapping("/summary/{idOrder}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable int idOrder){
        return ResponseEntity.ok(service.getSummary(idOrder));
    }
}
