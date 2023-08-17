package com.teamwork.boutique.service.imp;

import com.teamwork.boutique.payload.request.OrderDetailSaveRequest;
import com.teamwork.boutique.payload.response.OrderDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailServiceImp {
    public List<OrderDetailResponse> getByUserId(int userId);
    public  void save(List<OrderDetailSaveRequest> request);
    public void delete(int id);

}
