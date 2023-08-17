package com.teamwork.boutique.service.imp;

import com.teamwork.boutique.payload.request.OrderSaveRequest;
import com.teamwork.boutique.payload.response.OrderSaveResponse;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;

@Service
public interface OrderServiceImp {
    public void  save(OrderSaveRequest request);
}