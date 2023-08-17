package com.teamwork.boutique.repository;

import com.teamwork.boutique.entity.ProductEntity;
import com.teamwork.boutique.payload.response.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    ProductEntity findById(int productId);
    List<ProductEntity> findByCategoryId(int idCategory);
}
