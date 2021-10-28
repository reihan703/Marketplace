/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.models;

import javax.persistence.*;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "address")
    private String address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    /*

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "address")
    private String address;
    
    @ManyToOne
    private InventoryBuy inventoryBuy;
    
    @ManyToOne
    private InventorySell inventorySell;

    
    //Get and Set Relation
    public InventoryBuy getInventoryBuy() {
        return inventoryBuy;
    }

    public void setInventoryBuy(InventoryBuy inventoryBuy) {
        this.inventoryBuy = inventoryBuy;
    }

    public InventorySell getInventorySell() {
        return inventorySell;
    }

    public void setInventorySell(InventorySell inventorySell) {
        this.inventorySell = inventorySell;
    }
    
    //Public class User
    public User(){
    }
    
    public User(long id, String name, String phone, String email, String city, String address){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city= city;
        this.address= address;
    }

*/   
}
