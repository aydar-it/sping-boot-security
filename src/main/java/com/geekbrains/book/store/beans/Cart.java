package com.geekbrains.book.store.beans;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.OrderItem;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    @Getter
    private List<OrderItem> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void add(Book book) {
        boolean newItem = true;
        for (OrderItem i : items) {
            if (i.getBook().getId().equals(book.getId())) {
                i.setTotal(i.getTotal() + 1);
                newItem = false;
                break;
            }
        }
        if (newItem) {
            items.add(new OrderItem(book, 1));
        }
    }

    public void clear() {
        items.clear();
    }
}
