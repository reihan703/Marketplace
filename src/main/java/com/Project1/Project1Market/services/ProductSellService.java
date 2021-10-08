package com.Project1.Project1Market.services;

import com.Project1.Project1Market.interfaces.SellInterface;
import com.Project1.Project1Market.models.SellProduct;
import com.Project1.Project1Market.repositories.SellRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductSellService implements SellInterface{
	@Autowired
	private SellRepository sellRepo;
	
        @Override
	public void saveProductToDB(MultipartFile file,String name,String desc
			,int price, long id, String phone)
	{
		SellProduct p = new SellProduct();
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
        
        sellRepo.save(p);
	}
        
    @Override
    public List<SellProduct> getAll() {
        return sellRepo.findAll();
    }

    @Override
    public List<SellProduct> findByUserid(long user_id) {
        return sellRepo.findByUserid(user_id);
    }

    @Override
    public SellProduct getById(long id_Sell) {
        Optional< SellProduct> optional = sellRepo.findById(id_Sell);

        if (!optional.isPresent()) {
            throw new RuntimeException(" Product not found for id :: " + id_Sell);
        }
        
        SellProduct sellProduct = optional.get();
        return sellProduct;
    }

    @Override
    public void updateProductToDB(MultipartFile file, String name, 
            String desc, int price, long id, long id_sell, String phone) {
        
        SellProduct p = new SellProduct();
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
        p.setId_Sell(id_sell);
        p.setUser_Phone(phone);

        sellRepo.save(p);
    }

    @Override
    public void delete(long id) {
        this.sellRepo.deleteById(id);
    }
}