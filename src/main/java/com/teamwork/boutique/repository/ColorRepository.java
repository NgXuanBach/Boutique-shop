package com.teamwork.boutique.repository;

import com.teamwork.boutique.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ColorRepository extends JpaRepository<ColorEntity,Integer> {
    ColorEntity findById (int id);

}
