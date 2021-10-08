/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Victorio Adam
 */
@Entity
@Table(name="sell_order")
public class InventorySell {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Column(name="title")
    private String title;
    
    @Column(name="description")
    private String description;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    
    /*
 
    
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Sell;
    
    @Column(name="item_Name")
    private String item_Name;
    
    @Column(name="item_Desc")
    private String item_Desc;
    
    @Column(name="item_Price")
    private int item_Price;
    
    @OneToMany
    private User user;

    //Get and Set Relation
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public InventorySell(){
    
    }

    public InventorySell(Long id_Sell, String item_Name, String item_Desc, int item_Price){
        this.id_Sell=id_Sell;
        this.item_Name=item_Name;
        this.item_Desc=item_Desc;
        this.item_Price=item_Price;
    }
    
    
    public long getId_Sell() {
        return id_Sell;
    }

    public void setId_Sell(long id_Sell) {
        this.id_Sell = id_Sell;
    }

    public String getItem_Name() {
        return item_Name;
    }

    public void setItem_Name(String item_Name) {
        this.item_Name = item_Name;
    }

    public String getItem_Desc() {
        return item_Desc;
    }

    public void setItem_Desc(String item_Desc) {
        this.item_Desc = item_Desc;
    }

    public int getItem_Price() {
        return item_Price;
    }

    public void setItem_Price(int item_Price) {
        this.item_Price = item_Price;
    }
    

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Column(name="title")
    private String title;
    
    @Column(name="description")
    private String description;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
*/
}



