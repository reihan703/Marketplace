package com.Project1.Project1Market.services;

import com.Project1.Project1Market.interfaces.BuyInterface;
import com.Project1.Project1Market.models.BuyProduct;
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
			,int price, long id, String phone)
	{
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
	p.setItem_Desc(desc);
        p.setItem_Name(name);
        p.setItem_Price(price);
        p.setUserid(id);
        p.setUser_Phone(phone);
        
        buyRepository.save(p);
	}
    
    @Override
    public void updateProductToDB(MultipartFile file, String name, String desc, int price, 
            long id, long id_buy, String phone) {
        
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
        p.setItem_Desc(desc);
        p.setItem_Name(name);
        p.setItem_Price(price);
        p.setUserid(id);
        p.setId_Buy(id_buy);
        p.setUser_Phone(phone);

        buyRepository.save(p);
    }

    @Override
    public List<BuyProduct> getAll() {
        return buyRepository.findAll();
    }
    
    @Override
    public List<BuyProduct> findByUserid(long user_id) {
        return buyRepository.findByUserid(user_id);
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
}
