package com.Project1.Project1Market.services;


import com.Project1.Project1Market.models.SellProduct;
import com.Project1.Project1Market.repositories.SellRepository;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductSellService {
	@Autowired
	private SellRepository sellRepo;
	
	public void  saveProductToDB(MultipartFile file,String name,String description
			,int price, int id)
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
	p.setItem_Desc(description);
        p.setItem_Name(name);
        p.setItem_Price(price);
        p.setId(price);
        p.setId(id);
        
        sellRepo.save(p);
	}
        public List<SellProduct> getAllProduct()
	{
		return sellRepo.findAll();
	}
}