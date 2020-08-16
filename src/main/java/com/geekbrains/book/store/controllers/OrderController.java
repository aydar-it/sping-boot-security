package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.services.OrderService;
import com.geekbrains.book.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private Cart cart;
    private UserService userService;

    @GetMapping
    public String saveOrder(Principal principal) {
        orderService.addOrder(userService.findByUsername(principal.getName()).get());
        return "redirect:/books";
    }

    @GetMapping("/orders")
    public String orders(Principal principal, Model model) {
        model.addAttribute("count", orderService.getCountByUser(userService.findByUsername(principal.getName()).get()));
        return "orders";
    }

    @GetMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        orderService.deleteById(id);
        return "redirect:/books/cart";
    }
}
