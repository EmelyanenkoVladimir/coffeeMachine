package com.example.coffee.service;

import com.example.coffee.model.Counts;
import com.example.coffee.repository.CountsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountsService {
    private final CountsRepository countsRepository;

    public CountsService(com.example.coffee.repository.CountsRepository countsRepository) {
        this.countsRepository = countsRepository;
    }

    public Counts findById(Long id) {
        return countsRepository.findById(id).orElse(null);
    }

    public List<Counts> findAll(){
        return countsRepository.findAll();
    }

    public Counts saveCounts(Counts counts){
        return countsRepository.save(counts);
    }

    public void deleteById(Long id){
        countsRepository.deleteById(id);
    }
}
