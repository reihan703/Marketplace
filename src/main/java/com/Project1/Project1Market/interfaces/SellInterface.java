/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.interfaces;

import com.Project1.Project1Market.models.SellProduct;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Victorio Adam
 */
public interface SellInterface {  
    List<SellProduct> getAll();
    
    void  saveProductToDB(MultipartFile file, String name,
            String desc, int price, long id, String phone);
    
    List<SellProduct> findByUserid(long user_id);
    
    SellProduct getById(long id_Sell);
    
    void updateProductToDB(MultipartFile file, String name,
            String desc, int price, long id, long id_sell, String phone);
    
    void delete(long id);
}
