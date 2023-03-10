package com.kursach.service;

import com.kursach.model.User;

public interface UserService {

    void signUpUser(User user);

    User getUser(String login);
}
