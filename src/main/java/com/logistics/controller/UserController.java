package com.logistics.controller;

import com.logistics.entity.User;
import com.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/list")
    public String list(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }
    
    @GetMapping("/add")
    public String addForm() {
        return "user/add";
    }
    
    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> add(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.insert(user);
        result.put("success", success);
        return result;
    }
    
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/edit";
    }
    
    @PostMapping("/edit")
    @ResponseBody
    public Map<String, Object> edit(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.update(user);
        result.put("success", success);
        return result;
    }
    
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.deleteById(id);
        result.put("success", success);
        return result;
    }
    
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    
    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam String username, 
                                      @RequestParam String password,
                                      HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("currentUser", user);
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
        }
        return result;
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }
}
