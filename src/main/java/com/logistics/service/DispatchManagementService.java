package com.logistics.service;

import com.logistics.entity.DispatchManagement;
import com.logistics.repository.DispatchManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DispatchManagementService {
    
    @Autowired
    private DispatchManagementRepository dispatchManagementRepository;
    
    public List<DispatchManagement> findAll() {
        return dispatchManagementRepository.findAll();
    }
    
    public List<DispatchManagement> findAllWithDetails() {
        return dispatchManagementRepository.findAllWithDetails();
    }
    
    public DispatchManagement findById(Integer id) {
        return dispatchManagementRepository.findById(id);
    }
    
    public DispatchManagement findByOrderId(Integer orderId) {
        return dispatchManagementRepository.findByOrderId(orderId);
    }
    
    public boolean insert(DispatchManagement dispatchManagement) {
        return dispatchManagementRepository.insert(dispatchManagement) > 0;
    }
    
    public boolean update(DispatchManagement dispatchManagement) {
        return dispatchManagementRepository.update(dispatchManagement) > 0;
    }
    
    public boolean deleteById(Integer id) {
        return dispatchManagementRepository.deleteById(id) > 0;
    }
}
