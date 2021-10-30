/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.repositories;

import com.Project1.Project1Market.models.SuggestModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Victorio Adam
 */
@Repository
public interface FeedbackRepository extends JpaRepository<SuggestModel, Long>{
    @Query(value="SELECT * FROM suggest_form ORDER BY id DESC LIMIT 1", nativeQuery = true )
    SuggestModel findFirstRow();

    @Query(value="SELECT * FROM suggest_form ORDER BY id DESC LIMIT 1,1", nativeQuery = true )
    SuggestModel findSecondRow();
    
}
