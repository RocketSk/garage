package com.kursach.dao.impl;

import com.kursach.dao.DriverDAO;
import com.kursach.model.Auto;
import com.kursach.model.Driver;
import com.kursach.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {

    private final static String SQL_QUERY_GET_ALL_DRIVERS = "From " + Driver.class.getSimpleName();
    private final static String SQL_QUERY_GET_DRIVER_BY_ID = "select driver from Driver driver where driver.id = :ID";
    private final static String SQL_QUERY_GET_DRIVERS_BY_AUTO = "From Driver driver where driver.auto = :AUTO";
    private final static String ID_PARAM = "ID";
    private final static String AUTO_ID_PARAM = "AUTO";


    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void addDriver(Driver driver) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(driver);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Driver> getAll() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_ALL_DRIVERS, Driver.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Driver> getAllByAuto(Auto auto) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_DRIVERS_BY_AUTO)
                    .setParameter(AUTO_ID_PARAM, auto)
                    .list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Driver getById(Integer id) {
        return getDriverByID(id);
    }

    private Driver getDriverByID(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_DRIVER_BY_ID, Driver.class)
                    .setParameter(ID_PARAM, id)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Driver();
    }

    @Override
    public void updateDriver(Driver driver) {
        Transaction transaction = null;
        Driver newDriver = getById(driver.getId());
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.evict(newDriver);
            newDriver.setAuto(driver.getAuto());
            newDriver.setAutoNum(driver.getAutoNum());
            newDriver.setFirstName(driver.getFirstName());
            newDriver.setLastName(driver.getLastName());
            newDriver.setFathersName(driver.getFathersName());
            session.update(newDriver);
            transaction.commit();
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDriver(Integer id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(getById(id));
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
