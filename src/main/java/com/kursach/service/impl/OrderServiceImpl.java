package com.kursach.service.impl;

import com.kursach.dao.OrderDAO;
import com.kursach.dao.impl.OrderDAOImpl;
import com.kursach.model.Order;
import com.kursach.service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    @Override
    public List<Order> getAll() throws SQLException {
        return orderDAO.getAll();
    }

    @Override
    public Order getById(Integer id) {
        return orderDAO.getById(id);
    }

    @Override
    public void deleteOrder(Order order) {
        orderDAO.deleteOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }
}
