package com.logistics.controller;

import com.logistics.entity.Vehicle;
import com.logistics.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleService;
    
    @GetMapping("/list")
    public String list(Model model) {
        List<Vehicle> vehicles = vehicleService.findAll();
        model.addAttribute("vehicles", vehicles);
        return "vehicle/list";
    }
    
    @GetMapping("/add")
    public String addForm() {
        return "vehicle/add";
    }
    
    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> add(@RequestBody Vehicle vehicle) {
        Map<String, Object> result = new HashMap<>();
        boolean success = vehicleService.insert(vehicle);
        result.put("success", success);
        return result;
    }
    
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        Vehicle vehicle = vehicleService.findById(id);
        model.addAttribute("vehicle", vehicle);
        return "vehicle/edit";
    }
    
    @PostMapping("/edit")
    @ResponseBody
    public Map<String, Object> edit(@RequestBody Vehicle vehicle) {
        Map<String, Object> result = new HashMap<>();
        boolean success = vehicleService.update(vehicle);
        result.put("success", success);
        return result;
    }
    
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id) {
        Map<String, Object> result = new HashMap<>();
        boolean success = vehicleService.deleteById(id);
        result.put("success", success);
        return result;
    }
}
