/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.services;

import com.Project1.Project1Market.interfaces.InventoryBuyInterface;
import com.Project1.Project1Market.models.InventoryBuy;

import com.Project1.Project1Market.repositories.InventoryBuyRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Victorio Adam
 */
public class InventoryBuyService implements InventoryBuyInterface{

    @Autowired
    private InventoryBuyRepository inventoryBuyRepository;
    
    @Override
    public List<InventoryBuy> getAll() {
        return inventoryBuyRepository.findAll();
        
    }

    @Override
    public void store(InventoryBuy inventoryBuy) {
        this.inventoryBuyRepository.save(inventoryBuy);
    }

    @Override
    public InventoryBuy getById(long id) {
        Optional<InventoryBuy> optional = inventoryBuyRepository.findById(id);
        
        if (!optional.isPresent()) {
            throw new RuntimeException(" Item not found for id :: " + id);
        }

        InventoryBuy inventoryBuy = optional.get();
        return inventoryBuy;
    }

    @Override
    public void delete(long id, long user_id) {
        this.inventoryBuyRepository.deleteById(user_id, id);
    }
    
}
