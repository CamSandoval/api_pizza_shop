package com.platzi.pizza.persistence.repository;


import com.platzi.pizza.persistence.entity.Pizza.PizzaEntity;
import com.platzi.pizza.persistence.entity.Pizza.UpdatePizzaPriceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PizzaRepository extends ListCrudRepository<PizzaEntity,Integer> {
    Optional<PizzaEntity> findTopByAvailableTrueAndNameIgnoreCase(String name);
    Page<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(Pageable pageable, String description);
    Page<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(Pageable pageable, String description);
    Page<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price, Pageable pageable);

    @Query(value = """
            UPDATE pizza set price =:#{#newPricePizza.newPrice}\s
            WHERE id_pizza =:#{#newPricePizza.pizzaId}"""
            ,nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPricePizza")UpdatePizzaPriceDTO newPricePizza);
}
