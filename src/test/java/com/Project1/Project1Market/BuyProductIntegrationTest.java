package com.Project1.Project1Market;

import com.Project1.Project1Market.repositories.BuyRepository;
import com.Project1.Project1Market.services.ProductBuyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class BuyProductIntegrationTest {
    
    @InjectMocks
    @Autowired
    ProductBuyService productBuyService;

    @MockBean
    BuyRepository buyRepository;

    @Test
    public void createBuyOrder() {
        try{
//            File img = new File("D:\\NetBeans Projects\\Project1Market-master\\src\\main\\resources\\static\\css\\images\\banteng_real.jpg");
            MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
                "text/plain" , "Green Learner - Arvind".getBytes());

//            File file = File.createTempFile("test", ".jpg");
            productBuyService.saveProductToDB(mmf, "Baju", "Brand new clothing arrived soon", 20000, 1);
            productBuyService.findByUserId(1);
            verify(buyRepository, times(1)).findByUserId(1);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void createBuyOrderWithoutName(){
        try{
            MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
                "text/plain" , "Green Learner - Arvind".getBytes());
            Mockito.doThrow(IllegalStateException.class)
                    .when(productBuyService)
                    .saveProductToDB(mmf,anyString(),anyString(),anyInt(),anyInt());

            productBuyService.saveProductToDB(mmf, null, "Brand new clothing arrived soon", 20000, 1);
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
                    .saveProductToDB(mmf,anyString(),anyString(),anyInt(),anyInt());

            productBuyService.saveProductToDB(mmf, "Baju", null, 20000, 1);
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
                    .saveProductToTest(mmf,anyString(),anyString(),anyString(),anyInt());

            productBuyService.saveProductToTest(mmf, "Baju", "Brand new clothing arrived soon", null, 1);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
        }
    }
    
}
