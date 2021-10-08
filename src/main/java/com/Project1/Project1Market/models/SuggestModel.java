/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Victorio Adam
 */
@Entity
@Table(name = "suggest_form")
public class SuggestModel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Suggest;
    
    @Column(name = "user_id")
    private long id;
    
    @Column(name = "email_Suggest")
    private String email_Suggest;
    
    @Column(name = "content_Suggestion")
    private String content_Suggestion;

    public long getId_Suggest() {
        return id_Suggest;
    }

    public void setId_Suggest(long id_Suggest) {
        this.id_Suggest = id_Suggest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail_Suggest() {
        return email_Suggest;
    }

    public void setEmail_Suggest(String email_Suggest) {
        this.email_Suggest = email_Suggest;
    }

    public String getContent_Suggestion() {
        return content_Suggestion;
    }

    public void setContent_Suggestion(String content_Suggestion) {
        this.content_Suggestion = content_Suggestion;
    }
    
    
}