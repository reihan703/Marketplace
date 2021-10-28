/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.services;


import com.Project1.Project1Market.interfaces.UserInterface;
import com.Project1.Project1Market.models.User;
import com.Project1.Project1Market.repositories.UserRepository;
import java.math.BigInteger;
import java.security.MessageDigest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USER
 */
@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepositories;

    @Override
    public void register(User user) throws Exception {
        String hashed = this.hash(user.getPassword());
        user.setPassword(hashed);
        
        if (user.getName().length()>100){
            throw new Exception("Name is too long");
        }

        if (user.getCity().length()>100){
            throw new Exception("City is too long");
        }

        if (user.getAddress().length()>100){
            throw new Exception("Address is too long");
        }
        
        this.userRepositories.save(user);
    }

    @Override
    public User auth(String email, String password) throws Exception {
        User user = userRepositories.findByEmail(email);

        if (user == null) {
            return null;
        }

        if (!this.match(user.getPassword(), password)) {
            return null;
        }

        return user;
    }

    private String hash(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDiggest = md.digest(password.getBytes());

        BigInteger no = new BigInteger(1, messageDiggest);

        String hashText = no.toString(16);
        while (hashText.length() < 32) {
            hashText = "0" + hashText;
        }

        return hashText;
    }

    private boolean match(String password, String rawPassword)
            throws Exception {
        rawPassword = this.hash(rawPassword);
        return password.equals(rawPassword);
    }
}
