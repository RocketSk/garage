package com.kursach.dao;

import com.kursach.model.User;

public interface UserDAO {

    void signUpUser(User user);

    User getUser(String login);

}
