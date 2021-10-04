/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.services;

import com.Project1.Project1Market.interfaces.InventorySellInterface;
import com.Project1.Project1Market.models.InventorySell;
import com.Project1.Project1Market.repositories.InventorySellRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Victorio Adam
 */
@Service
public class InventorySellService implements InventorySellInterface{
    
    @Autowired
    private InventorySellRepository inventorySellRepository;
    
    @Override
    public List<InventorySell> getAll() {
        return inventorySellRepository.findAll();
        
    }

    @Override
    public void store(InventorySell inventorySell) {
        this.inventorySellRepository.save(inventorySell);
    }

    @Override
    public InventorySell getById(long id) {
        Optional<InventorySell> optional = inventorySellRepository.findById(id);
        
        if (!optional.isPresent()) {
            throw new RuntimeException(" Item not found for id :: " + id);
        }

        InventorySell inventorySell = optional.get();
        return inventorySell;
    }

    @Override
    public void delete(long user_id, long id) {
        this.inventorySellRepository.deleteById(user_id, id);
    }
    
}
