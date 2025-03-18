package com.logistics.service;

import com.logistics.entity.Order;
import com.logistics.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
    
    public List<Order> findAllWithDetails() {
        return orderRepository.findAllWithDetails();
    }
    
    public Order findById(Integer id) {
        return orderRepository.findById(id);
    }
    
    public boolean insert(Order order) {
        return orderRepository.insert(order) > 0;
    }
    
    public boolean update(Order order) {
        return orderRepository.update(order) > 0;
    }
    
    public boolean updateStatus(Integer id, String orderStatus) {
        return orderRepository.updateStatus(id, orderStatus) > 0;
    }
    
    public boolean deleteById(Integer id) {
        return orderRepository.deleteById(id) > 0;
    }
}
