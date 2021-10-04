/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.repositories;

import com.Project1.Project1Market.dto.LogResponse;
import com.Project1.Project1Market.models.InventorySell;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Victorio Adam
 */
@Repository
public interface InventorySellRepository extends JpaRepository<InventorySell, Long>{
    
    @Query("SELECT new com.Project1.Project1Market.dto.LogResponse(c.name , p.productName) FROM Customer c JOIN c.products p")
    public List<LogResponse> getJoinInformation();

    public void deleteById(long user_id, long id);
}
