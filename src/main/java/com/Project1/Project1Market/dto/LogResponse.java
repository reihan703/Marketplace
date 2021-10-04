/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.dto;

import com.Project1.Project1Market.interfaces.InventorySellInterface;
import org.springframework.beans.factory.annotation.Autowired;



/**
 *
 * @author Victorio Adam
 */

public class LogResponse {
    @Autowired
    private InventorySellInterface inventorySellInterface;
    
    private String item_Name;
    private String item_Desc;
    private String item_Type;
    private int item_Price;

    public LogResponse(String item_Name, String item_Desc) {
        this.item_Name = item_Name;
        this.item_Price = item_Price;
    }
}
