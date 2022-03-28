package com.example.coffee.service;

import com.example.coffee.model.Ordermenu;
import com.example.coffee.repository.OrdermenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdermenuService {
    private final OrdermenuRepository ordermenuRepository;

    public OrdermenuService(OrdermenuRepository ordermenuRepository) {
        this.ordermenuRepository = ordermenuRepository;
    }

    public Ordermenu findById(Long id) {
        return ordermenuRepository.findById(id).orElse(null);
    }

    public List<Ordermenu> findAll(){
        return ordermenuRepository.findAll();
    }

    public Ordermenu saveOrdermenu(Ordermenu ordermenu){
        return ordermenuRepository.save(ordermenu);
    }

    public void deleteById(Long id){
        ordermenuRepository.deleteById(id);
    }
}
