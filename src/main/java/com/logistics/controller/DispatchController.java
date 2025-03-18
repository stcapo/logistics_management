package com.logistics.controller;

import com.logistics.entity.DispatchManagement;
import com.logistics.entity.Order;
import com.logistics.entity.User;
import com.logistics.entity.Vehicle;
import com.logistics.service.DispatchManagementService;
import com.logistics.service.OrderService;
import com.logistics.service.UserService;
import com.logistics.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dispatch")
public class DispatchController {
    
    @Autowired
    private DispatchManagementService dispatchService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private VehicleService vehicleService;
    
    @GetMapping("/list")
    public String list(Model model) {
        List<DispatchManagement> dispatches = dispatchService.findAllWithDetails();
        model.addAttribute("dispatches", dispatches);
        return "dispatch/list";
    }
    
    @GetMapping("/add")
    public String addForm(Model model) {
        List<Order> orders = orderService.findAll();
        List<User> drivers = userService.findAll();
        List<Vehicle> vehicles = vehicleService.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("drivers", drivers);
        model.addAttribute("vehicles", vehicles);
        return "dispatch/add";
    }
    
    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> add(@RequestBody DispatchManagement dispatch) {
        Map<String, Object> result = new HashMap<>();
        boolean success = dispatchService.insert(dispatch);
        // Update order status to assigned when dispatch is created
        if (success) {
            orderService.updateStatus(dispatch.getOrderid(), "assigned");
        }
        result.put("success", success);
        return result;
    }
    
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        DispatchManagement dispatch = dispatchService.findById(id);
        List<Order> orders = orderService.findAll();
        List<User> drivers = userService.findAll();
        List<Vehicle> vehicles = vehicleService.findAll();
        model.addAttribute("dispatch", dispatch);
        model.addAttribute("orders", orders);
        model.addAttribute("drivers", drivers);
        model.addAttribute("vehicles", vehicles);
        return "dispatch/edit";
    }
    
    @PostMapping("/edit")
    @ResponseBody
    public Map<String, Object> edit(@RequestBody DispatchManagement dispatch) {
        Map<String, Object> result = new HashMap<>();
        boolean success = dispatchService.update(dispatch);
        result.put("success", success);
        return result;
    }
    
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id) {
        Map<String, Object> result = new HashMap<>();
        // Get order id before deletion to update its status
        DispatchManagement dispatch = dispatchService.findById(id);
        if (dispatch != null) {
            boolean success = dispatchService.deleteById(id);
            if (success) {
                // Set order back to pending status
                orderService.updateStatus(dispatch.getOrderid(), "pending");
            }
            result.put("success", success);
        } else {
            result.put("success", false);
            result.put("message", "找不到调度记录");
        }
        return result;
    }
}
