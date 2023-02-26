package com.kursach.dao.impl;

import com.kursach.dao.FuelDAO;
import com.kursach.model.Fuel;
import com.kursach.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuelDAOImpl implements FuelDAO {

    private final static String SQL_QUERY_GET_ALL_FUEL_TYPE = "From " + Fuel.class.getSimpleName();
    private final static String SQL_QUERY_GET_FUEL_BY_ID = "select fuel from Fuel fuel where fuel.id = :ID";

    private final static String ID_PARAM = "ID";


    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    @Override
    public void addFuel(Fuel fuel) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.save(fuel);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFuel(Fuel fuel) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(fuel);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Fuel> getAll() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_ALL_FUEL_TYPE).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Fuel getById(Integer id) {
        return getFuelByID(id);
    }

    private Fuel getFuelByID(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_FUEL_BY_ID, Fuel.class)
                    .setParameter(ID_PARAM, id)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Fuel();
    }

    @Override
    public void updateFuel(Fuel fuel) {
        Transaction transaction = null;
        Fuel newFuel = getById(fuel.getId());
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.evict(newFuel);
            newFuel.setFuelType(fuel.getFuelType());
            newFuel.setCount(fuel.getCount());
            session.update(newFuel);
            transaction.commit();
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
