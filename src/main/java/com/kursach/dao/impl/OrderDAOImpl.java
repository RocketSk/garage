package com.kursach.dao.impl;

import com.kursach.dao.OrderDAO;
import com.kursach.model.Order;
import com.kursach.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private final static String SQL_QUERY_GET_ALL_ORDERS = "From " + Order.class.getSimpleName();
    private final static String SQL_QUERY_GET_ORDER_BY_ID = "select order from Order order where order.id = :ID";
    private final static String ID_PARAM = "ID";

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void addOrder(Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAll() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_ALL_ORDERS).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Order getById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_ORDER_BY_ID, Order.class)
                    .setParameter(ID_PARAM, id)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Order();
    }

    @Override
    public void deleteOrder(Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrder(Order order) {
        Transaction transaction = null;
        Order newOrder = getById(order.getId());
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.evict(newOrder);
            newOrder.setFuelVolume(order.getFuelVolume());
            newOrder.setDriver(order.getDriver());
            newOrder.setAuto(order.getAuto());
            newOrder.setCoast(order.getCoast());
            newOrder.setStartPoint(order.getStartPoint());
            newOrder.setEndPoint(order.getEndPoint());
            newOrder.setFuel(order.getFuel());
            newOrder.setDistance(order.getDistance());
            session.update(newOrder);
            transaction.commit();
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
