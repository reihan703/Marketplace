package com.Project1.Project1Market.services;

import com.Project1.Project1Market.models.SellProduct;
import com.Project1.Project1Market.models.User;
import com.Project1.Project1Market.repositories.SellRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductSellService {

    @Autowired
    private SellRepository sellRepo;

    public void saveProductToDB(MultipartFile file, String name, String description,
             int price, int id) {
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
        p.setItem_Desc(description);
        p.setItem_Name(name);
        p.setItem_Price(price);
        p.setId(id);

        sellRepo.save(p);
    }

    public List<SellProduct> getAllProduct() {
        return sellRepo.findAll();
    }

    public List<SellProduct> findByUserid(long user_id) {
        return sellRepo.findByUserid(user_id);
    }

    public SellProduct getById(long id_Sell) {
        Optional< SellProduct> optional = sellRepo.findById(id_Sell);

        if (!optional.isPresent()) {
            throw new RuntimeException(" Product not found for id :: " + id_Sell);
        }

        SellProduct sellProduct = optional.get();
        return sellProduct;
    }

    public void updateProductToDB(MultipartFile file, String name, String description,
             int price, int id, int id_sell) {
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
        p.setItem_Desc(description);
        p.setItem_Name(name);
        p.setItem_Price(price);
        p.setId(id);
        p.setId_Sell(id_sell);

        sellRepo.save(p);
    }
    
    public void delete(long id) {
      this.sellRepo.deleteById(id);
    }
}
