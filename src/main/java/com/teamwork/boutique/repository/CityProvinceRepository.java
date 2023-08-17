package com.teamwork.boutique.repository;

import com.teamwork.boutique.entity.CityProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityProvinceRepository extends JpaRepository<CityProvinceEntity,Integer> {
}
