/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Victorio Adam
 */
@Entity
@Table(name="buy_order")
public class InventoryBuy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Sell;
    
    @Column(name="item_Name")
    private String item_Name;
    
    @Column(name="item_Desc")
    private String item_Desc;
    
    @Column(name="item_Price")
    private int item_Price;
    
    @Lob
    @Column(name ="picture_Buy")
    private String picture_Buy;
    
    @OneToMany(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private long user_id;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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
    
    public String getPicture_Buy() {
        return picture_Buy;
    }

    public void setPicture_Buy(String picture_Buy) {
        this.picture_Buy = picture_Buy;
    }

}
