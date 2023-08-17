package com.teamwork.boutique.service.imp;

import com.teamwork.boutique.payload.request.StockRequest;
import com.teamwork.boutique.payload.response.StockResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockServiceImp {
    List<StockResponse> getStockByProductId(int productId);
    List<StockResponse>getAllStock();
    String addStock(int colorId,int quantity,int productId,double price,String imageUrl);

    boolean updateStock(StockRequest request);
    boolean deletestock(int id);

}
