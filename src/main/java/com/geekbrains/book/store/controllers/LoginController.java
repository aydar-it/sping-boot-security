package com.geekbrains.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "login-page";
    }

    @GetMapping
    public String sh1owLoginForm() {
        return "redirect:/books";
    }
}
