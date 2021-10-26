package com.Project1.Project1Market;

import com.Project1.Project1Market.repositories.SellRepository;
import com.Project1.Project1Market.services.ProductSellService;
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
public class SellProductIntegrationTest {
    
    @InjectMocks
    @Autowired
    ProductSellService productSellService;

    @MockBean
    SellRepository sellRepository;

    @Test
    public void createSellOrder() {
        try{
//            File img = new File("D:\\NetBeans Projects\\Project1Market-master\\src\\main\\resources\\static\\css\\images\\banteng_real.jpg");
            MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
                "text/plain" , "Green Learner - Arvind".getBytes());

//            File file = File.createTempFile("test", ".jpg");
            productSellService.saveProductToDB(mmf, "Baju", "Brand new clothing arrived soon", 20000, 1);
            productSellService.findByUserId(1);
            verify(sellRepository, times(1)).findByUserId(1);
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
                    .when(productSellService)
                    .saveProductToDB(mmf,anyString(),anyString(),anyInt(),anyInt());

            productSellService.saveProductToDB(mmf, null, "Brand new clothing arrived soon", 20000, 1);
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
                    .saveProductToDB(mmf,anyString(),anyString(),anyInt(),anyInt());

            productSellService.saveProductToDB(mmf, "Baju", null, 20000, 1);
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
                    .saveProductToTest(mmf,anyString(),anyString(),anyString(),anyInt());

            productSellService.saveProductToTest(mmf, "Baju", "Brand new clothing arrived soon", null, 1);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
        }
    }
    
}
