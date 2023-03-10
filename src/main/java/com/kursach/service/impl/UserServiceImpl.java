package com.kursach.service.impl;

import com.kursach.dao.UserDAO;
import com.kursach.dao.impl.UserDAOImpl;
import com.kursach.model.User;
import com.kursach.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public void signUpUser(User user) {
        userDAO.signUpUser(user);
    }

    @Override
    public User getUser(String login) {
        return userDAO.getUser(login);
    }
}
