package com.example.coffee.service;

import com.example.coffee.model.OrderMenu;
import com.example.coffee.repository.OrdermenuRepository;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class OrdermenuService {
    private final OrdermenuRepository ordermenuRepository;

    public OrdermenuService(OrdermenuRepository ordermenuRepository) {
        this.ordermenuRepository = ordermenuRepository;
    }

    public OrderMenu findById(Long id) {
        return ordermenuRepository.findById(id).orElse(null);
    }

    public List<OrderMenu> findAll(){
        return ordermenuRepository.findAll();
    }

    public OrderMenu saveOrderMenu(OrderMenu ordermenu){
        return ordermenuRepository.save(ordermenu);
    }

    public void deleteById(Long id){
        ordermenuRepository.deleteById(id);
    }

    public List<String> findTypeOfOrder(){
        return ordermenuRepository.findTypeOfOrder();
    }
}
