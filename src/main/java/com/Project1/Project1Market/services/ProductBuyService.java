package com.Project1.Project1Market.services;

import com.Project1.Project1Market.interfaces.BuyInterface;
import com.Project1.Project1Market.models.BuyProduct;
import com.Project1.Project1Market.models.User;
import com.Project1.Project1Market.repositories.BuyRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductBuyService implements BuyInterface{
    @Autowired
    private BuyRepository buyRepository;
    
    @Override
    public void  saveProductToDB(MultipartFile file,String name,String desc
			,String price, long id) throws Exception
	{
                int priceProduct;
            
                User user = new User();
                user.setId(id);
            
		BuyProduct p = new BuyProduct();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
                
                if(price.equals("")){
                    throw new Exception("Price cannot be null");
                }

                priceProduct = Integer.parseInt(price);
                
	p.setItem_Desc(desc);
        p.setItem_Name(name);
        p.setItem_Price(priceProduct);
        p.setUser(user);
        
        buyRepository.save(p);
	}
    
    @Override
    public void updateProductToDB(MultipartFile file, String name, String desc, 
                String price, long id, long id_buy) throws Exception 
    {
            int priceProduct;
        
            User user = new User();
            user.setId(id);
                
            BuyProduct p = new BuyProduct();
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("not a a valid file");
            }
            try {
                p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            if(price.equals("")){
                throw new Exception("Price cannot be null");
            }
            
            priceProduct = Integer.parseInt(price);
            
            p.setItem_Desc(desc);
            p.setItem_Name(name);
            p.setItem_Price(priceProduct);
            p.setUser(user);
            p.setId_Buy(id_buy);

            buyRepository.save(p);
    }

    @Override
    public List<BuyProduct> getAll() {
        return buyRepository.findAll();
    }
    
    @Override
    public List<BuyProduct> findByUserId(long user_id) {
        return buyRepository.findByUserId(user_id);
    }

    @Override
    public BuyProduct getById(long id_Buy) {
        Optional< BuyProduct> optional = buyRepository.findById(id_Buy);

        if (!optional.isPresent()) {
            throw new RuntimeException(" Product not found for id :: " + id_Buy);
        }
        
        BuyProduct buyProduct = optional.get();
        return buyProduct;
    }

    @Override
    public void delete(long id) {
        this.buyRepository.deleteById(id);
    }
    
    public void saveProductToTest(MultipartFile file,String name,String desc
			,String priceProduct, long id) throws Exception
	{
            int price;
            
            User user = new User();
            user.setId(id);
            
            BuyProduct p = new BuyProduct();
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if(fileName.contains(".."))
            {
		System.out.println("not a a valid file");
            }
            try {
		p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
		e.printStackTrace();
            }
            
            if(priceProduct.equals("")){
                throw new Exception("Price cannot be null");
            }
            
            price = Integer.parseInt(priceProduct);
                           
	p.setItem_Desc(desc);
        p.setItem_Name(name);
        p.setItem_Price(price);
        p.setUser(user);
        
        buyRepository.save(p);
	}
}
