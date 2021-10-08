/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.services;

import com.Project1.Project1Market.interfaces.FeedbackInterface;
import com.Project1.Project1Market.models.SuggestModel;
import com.Project1.Project1Market.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Victorio Adam
 */
@Service
public class FeedbackService implements FeedbackInterface {
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    @Override
    public void postFeedbackToDB(long id, String email, String suggest){
        SuggestModel sm = new SuggestModel();
        
        sm.setId(id);
        sm.setEmail_Suggest(email);
        sm.setContent_Suggestion(suggest);
        
        feedbackRepository.save(sm);
    }
    
}
