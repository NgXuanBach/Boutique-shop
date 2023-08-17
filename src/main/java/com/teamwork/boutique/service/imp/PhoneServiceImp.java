package com.teamwork.boutique.service.imp;

import com.teamwork.boutique.payload.request.IdListRequest;
import com.teamwork.boutique.payload.request.PhoneSaveRequest;
import com.teamwork.boutique.payload.response.PhoneResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PhoneServiceImp {
    List<PhoneResponse> getByUserId(int userId);
    PhoneResponse save(PhoneSaveRequest request);
//    boolean deleteList(int[] phoneIdSet);
    boolean deleteList(IdListRequest request);
}
