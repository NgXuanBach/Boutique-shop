package com.teamwork.boutique.service;

import com.teamwork.boutique.entity.OrderDetailEntity;
import com.teamwork.boutique.entity.OrderEntity;
import com.teamwork.boutique.entity.StockEntity;
import com.teamwork.boutique.entity.UserEntity;
import com.teamwork.boutique.exception.CustomException;
import com.teamwork.boutique.payload.request.OrderDetailSaveRequest;
import com.teamwork.boutique.payload.response.OrderDetailResponse;
import com.teamwork.boutique.repository.OrderDetailRepository;
import com.teamwork.boutique.repository.OrderRepository;
import com.teamwork.boutique.repository.ProductRepository;
import com.teamwork.boutique.service.imp.OrderDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService implements OrderDetailServiceImp {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    public List<OrderDetailResponse> getByUserId(int userId) {
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
        for (OrderDetailEntity item : orderDetailRepository.findByUserId(userId)){
            OrderDetailResponse response = new OrderDetailResponse();
            response.setId(item.getId());
            response.setProductId(item.getStock().getProduct().getId());
            response.setProductName(item.getStock().getProduct().getName());
            response.setProductImage(item.getStock().getImage());
            response.setProductPrices(item.getStock().getPrice());
            response.setColorName(item.getStock().getColor().getName());
            response.setStockId(item.getStock().getId());
            response.setPrices(item.getPrice());
            response.setQuantity(item.getQuantity());
            response.setStatusId(item.getOrder().getStatus().getId());
            response.setStatusName(item.getOrder().getStatus().getName());
            orderDetailResponses.add(response);
        }

        return orderDetailResponses;
    }

    @Override
    public void save(List<OrderDetailSaveRequest> request) {
        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
        for(OrderDetailSaveRequest item : request){
            OrderDetailEntity entity = new OrderDetailEntity();
            entity.setOrder(new OrderEntity());
            entity.getOrder().setId(item.getOrderId());
            entity.setUser(new UserEntity());
            entity.getUser().setId(item.getUserId());
            entity.setStock(new StockEntity());
            entity.getStock().setId(item.getStockId());
            entity.setQuantity(item.getQuantity());
            entity.setPrice(item.getPrice());
            orderDetailEntities.add(entity);
        }
        orderDetailRepository.saveAll(orderDetailEntities);
    }

    @Override
    public void delete(int id) {
        try {
            orderDetailRepository.deleteById(id);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}
