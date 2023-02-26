package com.kursach.dao.impl;

import com.kursach.dao.AutoDAO;
import com.kursach.model.Auto;
import com.kursach.model.Driver;
import com.kursach.model.Fuel;
import com.kursach.service.DriverService;
import com.kursach.service.impl.DriverServiceImpl;
import com.kursach.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutoDAOImpl implements AutoDAO {

    private final static String SQL_QUERY_GET_ALL_AUTO = "From " + Auto.class.getSimpleName();
    private final static String SQL_QUERY_GET_AUTO_BY_ID = "select auto from Auto auto where auto.id = :ID";
    private final static String SQL_QUERY_GET_AUTO_BY_NUM = "select auto from Auto auto where auto.num = :NUM";

    private final static String SQL_QUERY_GET_AUTO_BY_FUEL = "From Auto auto where auto.fuel = :FUEL";

    private final static String ID_PARAM = "ID";
    private final static String NUM_PARAM = "NUM";
    private final static String FUEL_PARAM = "FUEL";

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final DriverService driverService = new DriverServiceImpl();


    @Override
    public Auto getByNum(String autoNum) {
        return getAutoByNum(autoNum);
    }

    @Override
    public void updateAuto(Auto auto) {
        Transaction transaction = null;
        Auto newAuto = getById(auto.getId());
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.evict(newAuto);
            newAuto.setNum(auto.getNum());
            newAuto.setBrand(auto.getBrand());
            newAuto.setModel(auto.getModel());
            newAuto.setFuel(auto.getFuel());
            List<Driver> driverList = driverService.getAllByAuto(auto);
            newAuto.setDrivers(driverList);
            session.update(newAuto);
            transaction.commit();
        } catch (HibernateException | SQLException e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAuto(Auto auto) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(auto);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Auto getById(Integer id) {
        return getAutoByID(id);
    }

    @Override
    public void addAuto(Auto auto) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(auto);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Auto> getAll() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_ALL_AUTO, Auto.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Auto getAutoByID(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Auto auto = session.createQuery(SQL_QUERY_GET_AUTO_BY_ID, Auto.class)
                    .setParameter(ID_PARAM, id)
                    .uniqueResult();
            List<Driver> driverList= driverService.getAllByAuto(auto);
            auto.setDrivers(driverList);
            return auto;
        } catch (HibernateException | SQLException e) {
            e.printStackTrace();
        }
        return new Auto();
    }

    private Auto getAutoByNum(String num) {
        try (Session session = sessionFactory.openSession()) {
            Auto auto = session.createQuery(SQL_QUERY_GET_AUTO_BY_NUM, Auto.class)
                    .setParameter(NUM_PARAM, num)
                    .uniqueResult();
            List<Driver> driverList= driverService.getAllByAuto(auto);
            auto.setDrivers(driverList);
            return auto;
        } catch (HibernateException | SQLException e) {
            e.printStackTrace();
        }
        return new Auto();
    }

    @Override
    public List<Fuel> getAllByFuel(Fuel fuel) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_AUTO_BY_FUEL)
                    .setParameter(FUEL_PARAM, fuel)
                    .list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
