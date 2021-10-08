/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "sell_order")
public class SellProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Sell;

    @Column(name = "item_Name")
    private String item_Name;

    @Column(name = "item_Desc")
    private String item_Desc;

    @Column(name = "item_Price")
    private int item_Price;

    @Column(name = "id")
    private long userid;
    
    @Column(name = "image", columnDefinition = "longblob")
    private String image;
    
    @Column(name = "user_Phone")
    private String user_Phone;

    public String getUser_Phone() {
        return user_Phone;
    }

    public void setUser_Phone(String user_Phone) {
        this.user_Phone = user_Phone;
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

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
   
    
    
    

    
}
