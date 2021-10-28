package com.Project1.Project1Market;

import com.Project1.Project1Market.models.SuggestModel;
import com.Project1.Project1Market.models.User;
import com.Project1.Project1Market.repositories.FeedbackRepository;
import com.Project1.Project1Market.services.FeedbackService;
import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class FeedbackIntegrationTest {
    
    @Autowired
    FeedbackService feedbackService;
    
    @MockBean
    FeedbackRepository feedbackRepository;
    
    @Test
    public void createFeedback() throws Exception {
        
        User user = new User();
        user.setId(1);
        
        SuggestModel suggestModel = new SuggestModel();
      
            suggestModel.setUser(user);
            suggestModel.setEmail_Suggest("test@gmail.com");
            suggestModel.setContent_Suggestion("yabadi");

            when(feedbackRepository.save(suggestModel)).thenReturn(suggestModel);
            feedbackService.postFeedbackToDB(suggestModel);

            when(feedbackRepository.findFirstRow()).thenReturn(suggestModel);

            SuggestModel checkFeedback = this.feedbackRepository.findFirstRow();

            Assertions.assertEquals(suggestModel, checkFeedback);
    }
    
    @Test
    public void createFeedbackWithEmptyEmail() throws Exception{
        try{
            
            User user = new User();
            user.setId(1);
            
            SuggestModel suggestModel = new SuggestModel();
            
            suggestModel.setUser(user);
            suggestModel.setEmail_Suggest("");
            suggestModel.setContent_Suggestion("yabadi");

            when(feedbackRepository.save(suggestModel))
                    .thenThrow(new IllegalArgumentException("Suggestion or Email cannot be null"));

            feedbackService.postFeedbackToDB(suggestModel);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("Suggestion or Email cannot be null", e.getMessage());
        }
    }
    
    @Test
    public void createFeedbackWithEmptyContent() throws Exception{
        try{
            User user = new User();
            user.setId(1);
            
            SuggestModel suggestModel = new SuggestModel();
            
            suggestModel.setUser(user);
            suggestModel.setEmail_Suggest("test@gmail.com");
            suggestModel.setContent_Suggestion("");

            when(feedbackRepository.save(suggestModel))
                    .thenThrow(new IllegalArgumentException("Suggestion or Email cannot be null"));

            feedbackService.postFeedbackToDB(suggestModel);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("Suggestion or Email cannot be null", e.getMessage());
        }
    }
    
    @Test
    public void createFeedbackWithEmptyBoth() throws Exception{
        
        try{
            User user = new User();
            user.setId(1);
            
            SuggestModel suggestModel = new SuggestModel();
            
            suggestModel.setUser(user);
            suggestModel.setEmail_Suggest("");
            suggestModel.setContent_Suggestion("");

            when(feedbackRepository.save(suggestModel))
                    .thenThrow(new IllegalArgumentException("Suggestion or Email cannot be null"));

            feedbackService.postFeedbackToDB(suggestModel);
            
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("Suggestion or Email cannot be null", e.getMessage());
        }
        
    }
}
