package com.Project1.Project1Market.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "suggest_form")
public class SuggestModel implements Serializable{
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Suggest;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "email_Suggest")
    private String email_Suggest;
    
    @Column(name = "content_Suggestion")
    private String content_Suggestion;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public long getId_Suggest() {
        return id_Suggest;
    }

    public void setId_Suggest(long id_Suggest) {
        this.id_Suggest = id_Suggest;
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