package com.teamwork.boutique.service;

import com.teamwork.boutique.entity.PhoneEntity;
import com.teamwork.boutique.entity.UserEntity;
import com.teamwork.boutique.exception.CustomException;
import com.teamwork.boutique.payload.request.PhoneSaveRequest;
import com.teamwork.boutique.payload.request.IdListRequest;
import com.teamwork.boutique.payload.response.PhoneResponse;
import com.teamwork.boutique.repository.PhoneRepository;
import com.teamwork.boutique.service.imp.PhoneServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneService implements PhoneServiceImp {
    @Autowired
    private PhoneRepository repository;

    @Override
    public List<PhoneResponse> getByUserId(int userId) {
        List<PhoneResponse> phoneResponses = new ArrayList<>();
        for (PhoneEntity item : repository.getByUserId(userId)) {
            PhoneResponse response = new PhoneResponse();
            response.setId(item.getId());
            response.setPhoneNumber(item.getPhoneNumber());
            response.setUserId(item.getUser().getId());
            phoneResponses.add(response);
        }
        return phoneResponses;
    }

    @Override
    public PhoneResponse save(PhoneSaveRequest request) {
        PhoneEntity entity = new PhoneEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(request.getUserId());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setUser(userEntity);
        entity = repository.saveAndFlush(entity);
        PhoneResponse response = new PhoneResponse();
        response.setId(entity.getId());
        return response;
    }
    @Override
//    @Transactional
    public boolean deleteList(IdListRequest request) {
        try {
            for (int id :request.getIdList()){
                repository.deleteById(id);
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new CustomException("Error delete list phone");
        }
    }
}
