package com.Project1.Project1Market.interfaces;


import com.Project1.Project1Market.models.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author USER
 */
public interface UserInterface {
    void register(User user) throws Exception;
    User auth(String email, String password) throws Exception;
}
