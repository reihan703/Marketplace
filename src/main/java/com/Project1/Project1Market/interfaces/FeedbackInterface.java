package com.Project1.Project1Market.interfaces;

import com.Project1.Project1Market.models.SuggestModel;

public interface FeedbackInterface {
    
    void postFeedbackToDB(SuggestModel suggestModel) throws Exception;
}
