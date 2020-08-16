package com.geekbrains.book.store.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id")
    private Order order;

    private int total;
    private BigDecimal price;

    public OrderItem(Book book, int total) {
        this.book = book;
        this.total = total;
        this.price = book.getPrice();
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", total=" + total +
                ", price=" + price +
                '}';
    }
}
