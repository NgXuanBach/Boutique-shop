package com.teamwork.boutique.repository;

import com.teamwork.boutique.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity,Integer> {
    List<PhoneEntity> getByUserId(int userId);
    List<PhoneEntity> getByUserIdAndAndPhoneNumber(int userId,String phoneNumber);
    PhoneEntity findById(int id);
    List<PhoneEntity> findAllById(int id);
}
