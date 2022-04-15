package com.example.coffee.repository;

import com.example.coffee.model.LevelOfIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelOfIngredientsRepository extends JpaRepository<LevelOfIngredients,Long> {
}
