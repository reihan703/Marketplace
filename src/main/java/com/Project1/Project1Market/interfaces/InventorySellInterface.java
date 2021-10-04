/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.interfaces;

import com.Project1.Project1Market.models.InventorySell;
import java.util.List;

/**
 *
 * @author Victorio Adam
 */
public interface InventorySellInterface {
    List<InventorySell> getAll();  
    void store(InventorySell inventorySell);
    InventorySell getById(long id);
    void delete(long user_id, long id);
}
