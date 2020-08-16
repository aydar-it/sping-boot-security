package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.utils.BookFilter;
import com.geekbrains.book.store.utils.Genre;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;
    private Cart cart;

    @GetMapping
    public String showPage(Model model,
                           @RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
                           @RequestParam Map<String, String> params
    ) {
        BookFilter bf = new BookFilter(params);
        Page<Book> page = bookService.findAll(bf.getSpec(), pageIndex - 1, 2);
        model.addAttribute("books", page);
        model.addAttribute("genres", Genre.values());
        model.addAttribute("map", params);
        return "book-store";
    }

    @GetMapping("/cart/{id}")
    public String addBookToCart(@PathVariable Long id) {
        bookService.addBookToCart(id);
        return "redirect:/books";
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("items", cart.getItems());
        return "cart";
    }
}
