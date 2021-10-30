package com.Project1.Project1Market;

import com.Project1.Project1Market.models.SellProduct;
import com.Project1.Project1Market.models.User;
import com.Project1.Project1Market.repositories.SellRepository;
import com.Project1.Project1Market.services.ProductSellService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class SellProductIntegrationTest {
    
    @InjectMocks
    @Autowired
    ProductSellService productSellService;

    @MockBean
    SellRepository sellRepository;

    @Test
    public void createSellOrder() throws Exception {
        
        MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
            "text/plain" , "Green Learner - Arvind".getBytes());

        User user = new User();
        user.setId(1);
        
        
        SellProduct product = new SellProduct();
        
        product.setImage(mmf.getOriginalFilename());
        product.setItem_Desc("Brand new clothing arrived soon");
        product.setItem_Name("Baju");
        product.setItem_Price(20000);
        product.setUser(user);
       
        when(sellRepository.save(product)).thenReturn(product);
        productSellService.saveProductToDB(mmf, "Baju", "Brand new clothing arrived soon", "20000", 1);
        
        List<SellProduct> listSell = Arrays.asList(product);

        when(sellRepository.findByUserId(1)).thenReturn(listSell);
        List<SellProduct> product2 = productSellService.findByUserId(1);
        
        Assertions.assertEquals(Arrays.asList(product), product2);
        
    }

    @Test
    public void createBuyOrderWithoutName(){
        try{
            MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
                "text/plain" , "Green Learner - Arvind".getBytes());
            Mockito.doThrow(IllegalStateException.class)
                    .when(productSellService)
                    .saveProductToDB(mmf,anyString(),anyString(),anyString(),anyInt());

            productSellService.saveProductToDB(mmf, null, "Brand new clothing arrived soon", "20000", 1);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
        }
    }

    @Test
    public void createSellOrderWithoutDesc(){
        try{
            MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
                "text/plain" , "Green Learner - Arvind".getBytes());
            Mockito.doThrow(IllegalStateException.class)
                    .when(productSellService)
                    .saveProductToDB(mmf,anyString(),anyString(),anyString(),anyInt());

            productSellService.saveProductToDB(mmf, "Baju", null, "20000", 1);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
        }
    }

    @Test
    public void createBuyOrderWithoutPrice(){
        try{
            MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
                "text/plain" , "Green Learner - Arvind".getBytes());
            Mockito.doThrow(IllegalStateException.class)
                    .when(productSellService)
                    .saveProductToDB(mmf,anyString(),anyString(),anyString(),anyInt());

            productSellService.saveProductToDB(mmf, "Baju", "Brand new clothing arrived soon", null, 1);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
        }
    }
    
    //Delete
    @Test
    public void DeleteBuyProduct(){
        long id = 1;
        doNothing().when(sellRepository).deleteById(id);
        productSellService.delete(id);
        
        verify(sellRepository, times(1)).deleteById(id);
    }
    
    //Edit
    @Test
    public void TestUsingUpdateMethod() throws Exception {
        
        MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
            "text/plain" , "Green Learner - Arvind".getBytes());

        User user = new User();
        user.setId(1);
        
        
        SellProduct product = new SellProduct();
        
        product.setImage(mmf.getOriginalFilename());
        product.setItem_Desc("Brand new clothing arrived soon");
        product.setItem_Name("Baju");
        product.setItem_Price(20000);
        product.setUser(user);
        product.setId_Sell(2);
       
        when(sellRepository.save(product)).thenReturn(product);
        productSellService.updateProductToDB(mmf, "Baju", "Brand new clothing arrived soon", "20000", 1,2);
        
        List<SellProduct> listBuy = Arrays.asList(product);

        when(sellRepository.findByUserId(1)).thenReturn(listBuy);
        List<SellProduct> product2 = productSellService.findByUserId(1);
        
        Assertions.assertEquals(Arrays.asList(product), product2);
        
    }
}
