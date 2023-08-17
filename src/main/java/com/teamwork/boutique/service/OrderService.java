package com.teamwork.boutique.service;

import com.teamwork.boutique.entity.*;
import com.teamwork.boutique.exception.CustomException;
import com.teamwork.boutique.payload.request.OrderDetailSaveRequest;
import com.teamwork.boutique.payload.request.OrderSaveRequest;
import com.teamwork.boutique.payload.response.OrderDetailResponse;
import com.teamwork.boutique.payload.response.OrderSaveResponse;
import com.teamwork.boutique.repository.*;
import com.teamwork.boutique.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements OrderServiceImp {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PhoneRepository phoneRepository;

    @Transactional
    @Override
    public void save(OrderSaveRequest request) {

        OrderEntity orderEntity = new OrderEntity();
        try {
            AddressEntity address = addressRepository.findById(request.getAddressId());
            orderEntity.setAddressDetail(address.getDetail());
            if(address.getCityProvince().getId()!=64){
                orderEntity.setAddressDetail(orderEntity.getAddressDetail()+","+address.getCountry().getName());
            }else {
                orderEntity.setAddressDetail(orderEntity.getAddressDetail()+","+address.getCityProvince().getName()+","+address.getCountry().getName());
            }
        }catch (Exception e){
            throw new CustomException("Address is not exists.",500);
        }
        orderEntity.setUser(new UserEntity());
        orderEntity.getUser().setId(request.getUserId());


        try {
           PhoneEntity phone = phoneRepository.findById(request.getPhoneId());
            if(phone.getId()== request.getPhoneId()){
                orderEntity.setPhoneNumber(phone.getPhoneNumber());
            }



        }catch (Exception e){
            throw new CustomException("Phone is not exists.",500);
        }
        orderEntity.setStatus(new StatusEntity());
        orderEntity.getStatus().setId(request.getStatusId());
        orderEntity.setTotal(request.getTotal());
        orderEntity = repository.saveAndFlush(orderEntity);
        if (orderEntity == null) {
            throw new CustomException("Error add Order");
        }
        // create list stock need to checking
        List<Integer> idStocks = new ArrayList<>();
        for (OrderDetailSaveRequest item : request.getOrderDetailSaveRequests()) {
            idStocks.add(item.getStockId());
        }
        // get list stock from database
        List<StockEntity> stockEntities = stockRepository.findByIdIn(idStocks);
        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
        // check list stock valid and update quantity stockList?
        for (OrderDetailSaveRequest orderDetailItem : request.getOrderDetailSaveRequests()) {
            // create list OrderDetail
            OrderDetailEntity entity = new OrderDetailEntity();
            entity.setOrder(new OrderEntity());
            entity.getOrder().setId(orderEntity.getId());
            entity.setUser(new UserEntity());
            entity.getUser().setId(orderEntity.getUser().getId());
            entity.setStock(new StockEntity());
            entity.getStock().setId(orderDetailItem.getStockId());
            entity.setQuantity(orderDetailItem.getQuantity());
            entity.setPrice(orderDetailItem.getPrice());
            orderDetailEntities.add(entity);
            // check valid and update
            for (StockEntity stockItem : stockEntities) {

                if (stockItem.getId() == orderDetailItem.getStockId()) {
                    if (stockItem.getQuantity() >= orderDetailItem.getQuantity()) {
                        stockItem.getProduct().setSoldQuantity(stockItem.getProduct().getSoldQuantity() + stockItem.getQuantity());
                        stockItem.setQuantity(stockItem.getQuantity() - orderDetailItem.getQuantity());
                        break;
                    } else {
                        throw new CustomException(stockItem.getProduct().getName() + "with " + stockItem.getColor().getName() + " no longer available in sufficient quantity");
                    }
                }
            }
        }
        stockRepository.saveAll(stockEntities);
        orderDetailRepository.saveAll(orderDetailEntities);
    }
}
