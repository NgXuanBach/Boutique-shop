package com.teamwork.boutique.service.imp;

import com.teamwork.boutique.payload.request.AddressSaveRequest;
import com.teamwork.boutique.payload.request.IdListRequest;
import com.teamwork.boutique.payload.response.AddressResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressServiceImp {
    List<AddressResponse> getByUserId(int userId);
    AddressResponse save(AddressSaveRequest request);
    boolean deleteList(IdListRequest request);
}
