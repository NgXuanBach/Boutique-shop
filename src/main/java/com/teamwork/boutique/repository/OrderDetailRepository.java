package com.teamwork.boutique.repository;

import com.teamwork.boutique.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Integer> {
    List<OrderDetailEntity> findByUserId(int userId);
}
