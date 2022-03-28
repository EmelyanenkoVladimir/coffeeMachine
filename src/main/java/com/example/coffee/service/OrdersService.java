package com.example.coffee.service;
import com.example.coffee.model.Orders;
import com.example.coffee.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Orders findById(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }

    public List<Orders> findAll(){
        return ordersRepository.findAll();
    }

    public Orders saveOrders(Orders orders){
        return ordersRepository.save(orders);
    }

    public void deleteById(Long id){
        ordersRepository.deleteById(id);
    }
}
