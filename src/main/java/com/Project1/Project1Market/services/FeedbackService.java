package com.Project1.Project1Market.services;

import com.Project1.Project1Market.interfaces.FeedbackInterface;
import com.Project1.Project1Market.models.SuggestModel;
import com.Project1.Project1Market.repositories.FeedbackRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService implements FeedbackInterface {
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    @Override
    public void postFeedbackToDB(SuggestModel suggestModel) throws Exception{
        
        if(suggestModel.getContent_Suggestion().equals("") || suggestModel.getEmail_Suggest().equals("")){
            throw new Exception("Suggestion or Email cannot be null");
        }
        
        feedbackRepository.save(suggestModel);
    }
    
    public SuggestModel firstRow() {
        return feedbackRepository.findFirstRow();
    }

    public SuggestModel secondRow() {
        return feedbackRepository.findSecondRow();
    }
    
}
