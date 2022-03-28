package com.example.coffee.service;

import com.example.coffee.model.Counts;
import com.example.coffee.model.Quantityofingredients;
import com.example.coffee.repository.QuantityofingredientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityofingredientsService {
    private final QuantityofingredientsRepository quantityofingredientsRepository;

    public QuantityofingredientsService(QuantityofingredientsRepository quantityofingredientsRepository) {
        this.quantityofingredientsRepository = quantityofingredientsRepository;
    }

    public Quantityofingredients findById(Long id) {
        return quantityofingredientsRepository.findById(id).orElse(null);
    }

    public List<Quantityofingredients> findAll(){
        return quantityofingredientsRepository.findAll();
    }

    public Quantityofingredients saveQuantityofingredients(Quantityofingredients quantityofingredients){
        return quantityofingredientsRepository.save(quantityofingredients);
    }

    public void deleteById(Long id){
        quantityofingredientsRepository.deleteById(id);
    }
}
