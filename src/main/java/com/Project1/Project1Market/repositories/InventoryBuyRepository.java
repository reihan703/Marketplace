/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.repositories;

import com.Project1.Project1Market.models.InventoryBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Victorio Adam
 */
@Repository
public interface InventoryBuyRepository extends JpaRepository<InventoryBuy, Long>{

    public void deleteById(long user_id, long id);
    
}
