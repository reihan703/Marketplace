package com.Project1.Project1Market.interfaces;

import com.Project1.Project1Market.models.BuyProduct;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface BuyInterface {
    List<BuyProduct> getAll();
    
    void  saveProductToDB(MultipartFile file,String name,String desc
			,String price, long id) throws Exception;
    
    List<BuyProduct> findByUserId(long user_id);
    
    BuyProduct getById(long id_Buy);
    
    void updateProductToDB(MultipartFile file, String name, String desc, String price, 
            long id, long id_buy) throws Exception;
    
    void delete(long id);
}
