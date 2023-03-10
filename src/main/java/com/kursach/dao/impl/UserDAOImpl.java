package com.kursach.dao.impl;

import com.kursach.dao.UserDAO;
import com.kursach.model.User;
import com.kursach.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDAOImpl implements UserDAO {

    private final static String SQL_QUERY_GET_USER_BY_LOGIN = "select user from User user where user.username = :LOGIN";

    private final static String LOGIN_PARAM = "LOGIN";


    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    @Override
    public void signUpUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String login) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_USER_BY_LOGIN, User.class)
                    .setParameter(LOGIN_PARAM, login)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new User();
    }
}
