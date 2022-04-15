package com.example.coffee.service;

import com.example.coffee.model.LevelOfIngredients;
import com.example.coffee.repository.LevelOfIngredientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelOfIngredientsService {
    private final LevelOfIngredientsRepository levelOfIngredientsRepository;

    public LevelOfIngredientsService(LevelOfIngredientsRepository levelOfIngredientsRepository) {
        this.levelOfIngredientsRepository = levelOfIngredientsRepository;
    }

    public LevelOfIngredients findById(Long id) {
        return levelOfIngredientsRepository.findById(id).orElse(null);
    }

    public List<LevelOfIngredients> findAll(){
        return levelOfIngredientsRepository.findAll();
    }

    public LevelOfIngredients saveLevelOfIngredients(LevelOfIngredients levelOfIngredients){
        return levelOfIngredientsRepository.save(levelOfIngredients);
    }

    public void deleteById(Long id){
        levelOfIngredientsRepository.deleteById(id);
    }
}
