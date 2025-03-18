package com.logistics.controller;

import com.logistics.entity.Order;
import com.logistics.entity.User;
import com.logistics.entity.Vehicle;
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
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private VehicleService vehicleService;
    
    @GetMapping("/list")
    public String list(Model model) {
        List<Order> orders = orderService.findAllWithDetails();
        model.addAttribute("orders", orders);
        return "order/list";
    }
    
    @GetMapping("/add")
    public String addForm(Model model) {
        List<User> users = userService.findAll();
        List<Vehicle> vehicles = vehicleService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("vehicles", vehicles);
        return "order/add";
    }
    
    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> add(@RequestBody Order order) {
        Map<String, Object> result = new HashMap<>();
        boolean success = orderService.insert(order);
        result.put("success", success);
        return result;
    }
    
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        Order order = orderService.findById(id);
        List<User> users = userService.findAll();
        List<Vehicle> vehicles = vehicleService.findAll();
        model.addAttribute("order", order);
        model.addAttribute("users", users);
        model.addAttribute("vehicles", vehicles);
        return "order/edit";
    }
    
    @PostMapping("/edit")
    @ResponseBody
    public Map<String, Object> edit(@RequestBody Order order) {
        Map<String, Object> result = new HashMap<>();
        boolean success = orderService.update(order);
        result.put("success", success);
        return result;
    }
    
    @PostMapping("/status/{id}")
    @ResponseBody
    public Map<String, Object> updateStatus(@PathVariable("id") Integer id, 
                                          @RequestParam String status) {
        Map<String, Object> result = new HashMap<>();
        boolean success = orderService.updateStatus(id, status);
        result.put("success", success);
        return result;
    }
    
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id) {
        Map<String, Object> result = new HashMap<>();
        boolean success = orderService.deleteById(id);
        result.put("success", success);
        return result;
    }
}
