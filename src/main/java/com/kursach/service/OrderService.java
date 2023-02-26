package com.kursach.service;

import com.kursach.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    void addOrder(Order order);

    List<Order> getAll() throws SQLException;

    Order getById(Integer id);

    void deleteOrder(Order order);

    void updateOrder(Order order);


}
