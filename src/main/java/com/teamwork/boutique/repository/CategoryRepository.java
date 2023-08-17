package com.teamwork.boutique.repository;


import com.teamwork.boutique.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    CategoryEntity findById(int id);
}