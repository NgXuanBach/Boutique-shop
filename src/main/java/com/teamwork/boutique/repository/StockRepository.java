package com.teamwork.boutique.repository;

import com.teamwork.boutique.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<StockEntity,Integer>{
    StockEntity findByProductIdAndColorId(int productId, int colorId);
    @Query("SELECT MIN(s.price) FROM  stock s WHERE s.product.id = ?1")
    double findMinPriceByProductId(int productId);
//    @Query(value = "select s.id,s.color.id,s.color.name,s.quantity,s.image from stock s where s.product.id=?1")
    List<StockEntity> findByProductId(int productId);
    List<StockEntity> findByIdIn(List<Integer> ids);
    StockEntity findById(int id);


}
