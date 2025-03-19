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
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private VehicleService vehicleService;
    
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        // Get dashboard data
        List<Order> recentOrders = orderService.findAllWithDetails();
        List<User> users = userService.findAll();
        List<Vehicle> vehicles = vehicleService.findAll();
        
        model.addAttribute("ordersCount", recentOrders.size());
        model.addAttribute("usersCount", users.size());
        model.addAttribute("vehiclesCount", vehicles.size());
        model.addAttribute("recentOrders", recentOrders);
        
        return "index";
    }
    
    @GetMapping("/index")
    public String index() {
        return "redirect:/";
    }
    
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
