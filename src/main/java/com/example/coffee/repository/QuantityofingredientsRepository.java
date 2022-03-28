package com.example.coffee.repository;

import com.example.coffee.model.Quantityofingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityofingredientsRepository extends JpaRepository<Quantityofingredients,Long> {
}
