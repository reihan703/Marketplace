/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.repositories;

import com.Project1.Project1Market.models.SellProduct;
import com.Project1.Project1Market.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Victorio Adam
 */
@Repository
public interface SellRepository extends JpaRepository<SellProduct, Long>{
}
