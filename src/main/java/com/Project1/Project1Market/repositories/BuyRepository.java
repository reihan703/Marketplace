package com.Project1.Project1Market.repositories;

import com.Project1.Project1Market.models.BuyProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRepository extends JpaRepository<BuyProduct, Long>{
    List<BuyProduct> findByUserId(long user_id);
}
