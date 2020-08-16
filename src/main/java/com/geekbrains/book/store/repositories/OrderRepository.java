package com.geekbrains.book.store.repositories;

import com.geekbrains.book.store.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;;

public interface OrderRepository  extends JpaRepository<Order, Long> {
    @Query(value = "select count(*) from orders where user_id = :id", nativeQuery = true)
    long getCountByUserId(@Param("id") Long id);
}
