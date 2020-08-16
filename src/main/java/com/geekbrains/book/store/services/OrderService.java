package com.geekbrains.book.store.services;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.repositories.OrderItemRepository;
import com.geekbrains.book.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderItemRepository orderItemRepository;
    private OrderRepository orderRepository;
    private Cart cart;

    public void addOrder(User user) {
        Order order = orderRepository.save(new Order(user));
        cart.getItems().forEach(e -> e.setOrder(order));
        cart.getItems().forEach(e -> orderItemRepository.saveAll(cart.getItems()));
        cart.clear();
    }

    public Long getCountByUser(User user) {
        return orderRepository.getCountByUserId(user.getId());
    }

    public void deleteById(Long id) {
        List<OrderItem> items = cart.getItems();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getBook().getId().equals(id)) {
                items.remove(i);
                break;
            }
        }
    }
}
