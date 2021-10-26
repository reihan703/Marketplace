package com.Project1.Project1Market.interfaces;

import com.Project1.Project1Market.models.User;

public interface UserInterface {
    void register(User user) throws Exception;
    User auth(String email, String password) throws Exception;
}
