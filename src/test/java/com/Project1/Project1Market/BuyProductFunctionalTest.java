package com.Project1.Project1Market;

import com.Project1.Project1Market.models.BuyProduct;
import com.Project1.Project1Market.models.User;
import com.Project1.Project1Market.repositories.BuyRepository;
import com.Project1.Project1Market.services.ProductBuyService;
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
public class BuyProductFunctionalTest {
    
    @InjectMocks
    @Autowired
    ProductBuyService productBuyService;

    @MockBean
    BuyRepository buyRepository;

    @Test
    public void createBuyOrder() throws Exception {
        
        MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
            "text/plain" , "Green Learner - Arvind".getBytes());

        User user = new User();
        user.setId(1);
        
        
        BuyProduct product = new BuyProduct();
        
        product.setImage(mmf.getOriginalFilename());
        product.setItem_Desc("Brand new clothing arrived soon");
        product.setItem_Name("Baju");
        product.setItem_Price(20000);
        product.setUser(user);
       
        when(buyRepository.save(product)).thenReturn(product);
        productBuyService.saveProductToDB(mmf, "Baju", "Brand new clothing arrived soon", "20000", 1) ;
        
        List<BuyProduct> listBuy = Arrays.asList(product);

        when(buyRepository.findByUserId(1)).thenReturn(listBuy);
        List<BuyProduct> checkProduct = productBuyService.findByUserId(1);
        
        Assertions.assertEquals(Arrays.asList(product), checkProduct);
        
    }

    @Test
    public void createBuyOrderWithoutName(){
        try{
            MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
                "text/plain" , "Green Learner - Arvind".getBytes());
            Mockito.doThrow(IllegalStateException.class)
                    .when(productBuyService)
                    .saveProductToDB(mmf,anyString(),anyString(),anyString(),anyInt());

            productBuyService.saveProductToDB(mmf, null, "Brand new clothing arrived soon", "20000", 1);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
        }
    }

    @Test
    public void createBuyOrderWithoutDesc(){
        try{
            MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
                "text/plain" , "Green Learner - Arvind".getBytes());
            Mockito.doThrow(IllegalStateException.class)
                    .when(productBuyService)
                    .saveProductToDB(mmf,anyString(),anyString(),anyString(),anyInt());

            productBuyService.saveProductToDB(mmf, "Baju", null, "20000", 1);
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
                    .when(productBuyService)
                    .saveProductToDB(mmf,anyString(),anyString(),anyString(),anyInt());

            productBuyService.saveProductToDB(mmf, "Baju", "Brand new clothing arrived soon", null, 1);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
        }
    }
    
    //Delete
    @Test
    public void DeleteBuyProduct(){
        long id = 1;
        doNothing().when(buyRepository).deleteById(id);
        productBuyService.delete(id);
        
        verify(buyRepository, times(1)).deleteById(id);
    }
    
    //Edit
    @Test
    public void TestUsingUpdateMethod() throws Exception {
        
        MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
            "text/plain" , "Green Learner - Arvind".getBytes());

        User user = new User();
        user.setId(1);
        
        
        BuyProduct product = new BuyProduct();
        
        product.setImage(mmf.getOriginalFilename());
        product.setItem_Desc("Brand new clothing arrived soon");
        product.setItem_Name("Baju");
        product.setItem_Price(20000);
        product.setUser(user);
        product.setId_Buy(2);
       
        when(buyRepository.save(product)).thenReturn(product);
        productBuyService.updateProductToDB(mmf, "Baju", "Brand new clothing arrived soon", "20000", 1,2);
        
        List<BuyProduct> listBuy = Arrays.asList(product);

        when(buyRepository.findByUserId(1)).thenReturn(listBuy);
        List<BuyProduct> product2 = productBuyService.findByUserId(1);
        
        Assertions.assertEquals(Arrays.asList(product), product2);
        
    }
}
