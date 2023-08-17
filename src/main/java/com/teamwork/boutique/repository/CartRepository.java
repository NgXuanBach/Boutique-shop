package com.teamwork.boutique.repository;

import com.teamwork.boutique.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    CartEntity findByStockIdAndUserId(int stockId, int userId);
    CartEntity findByStockId(int stockId);
    CartEntity findById(int id);

    List<CartEntity> findByUserId(int userId);
    CartEntity deleteById(int id);
    void deleteByIdIn(Set<Integer> ids);
}