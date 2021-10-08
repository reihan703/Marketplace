/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.interfaces;

/**
 *
 * @author Victorio Adam
 */
public interface FeedbackInterface {
    
    void postFeedbackToDB(long id, String email, String suggest);
}
