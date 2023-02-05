package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute User user) {
        return "users/add";
    }

    @PostMapping("/add")
    public String create(User user) {
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable int id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "users/view";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(User user, @PathVariable int id) {
        userService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
