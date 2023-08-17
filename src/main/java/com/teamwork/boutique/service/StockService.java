package com.teamwork.boutique.service;

import com.teamwork.boutique.entity.ColorEntity;
import com.teamwork.boutique.entity.ProductEntity;
import com.teamwork.boutique.entity.StockEntity;
import com.teamwork.boutique.exception.CustomException;
import com.teamwork.boutique.payload.request.StockRequest;
import com.teamwork.boutique.payload.response.StockResponse;
import com.teamwork.boutique.repository.ColorRepository;
import com.teamwork.boutique.repository.ProductRepository;
import com.teamwork.boutique.repository.StockRepository;
import com.teamwork.boutique.service.imp.StockServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService implements StockServiceImp {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<StockResponse> getStockByProductId(int productId) {
        List<StockResponse> stockResponses = new ArrayList<>();
        for (StockEntity item : stockRepository.findByProductId(productId)){
            StockResponse response = new StockResponse();
//            response.setId(item.getId());
            response.setColorName(item.getColor().getName());
            response.setQuantity(item.getQuantity());
            response.setPrice(item.getPrice());
            response.setImage(item.getImage());
            response.setColorId(item.getColor().getId());
            stockResponses.add(response);
        }
        return stockResponses;
    }
    @Override
    public List<StockResponse> getAllStock() {
        List<StockResponse>responselist=new ArrayList<>();
        List<StockEntity> list =stockRepository.findAll();


        for(StockEntity data: list) {
            StockResponse stockResponse = new StockResponse();
            stockResponse.setId(data.getId());
            stockResponse.setColorId(data.getColor().getId());
            stockResponse.setQuantity(data.getQuantity());
            stockResponse.setProductId(data.getProduct().getId());
            stockResponse.setPrice(data.getPrice());
            stockResponse.setImage(data.getImage());
            responselist.add(stockResponse);
        }
        return responselist;    }

    @Override
    public String addStock(int colorId, int quantity, int productId, double price, String imageUrl) {
        String message = "";
        boolean check = true;
//        if (catename != null && !catename.isEmpty()) {
//            for (CategoryRespone categoryResponse : getAllCategory()) {
//                if (categoryResponse.getName().equalsIgnoreCase(catename)) {
//                    message = "This name have existed already. Please type another name !";
//                    check = false;
//                }
//            }
        if (check) {
            StockEntity stock = new StockEntity();
            stock.setQuantity(quantity);
            ColorEntity colorID = colorRepository.findById(colorId);
            stock.setColor(colorID);
            ProductEntity ProductID = productRepository.findById(productId);
            stock.setProduct(ProductID);
            stock.setPrice(price);
            stock.setImage(imageUrl);
            stockRepository.save(stock);

            message = "Save successfully !";
        }
//        } else {
//            message = "You have not type category yet. Please type category before click Create button!";
//        }
        return message;
    }

    @Override
    public boolean updateStock(StockRequest request) {
        boolean isSuccess = false;
        try {
            System.out.println("da thuc hien lenh");
            StockEntity stock = new StockEntity();
            try {
                stock.setId(request.getId());

            } catch (Exception e) {

            }
            stock.setPrice(request.getPrice());
            stock.setImage(request.getImage());
            stock.setQuantity(request.getQuantity());
            ProductEntity product = productRepository.findById(request.getProductId());
            stock.setProduct(product);

            ColorEntity color = colorRepository.findById(request.getColorId());
            stock.setColor(color);
            stockRepository.save(stock);

            isSuccess = true;
            if (isSuccess) {
                System.out.println("da thanh cong");
            } else {
                System.out.println("da that bai");
            }
            List<StockResponse> list = getAllStock();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean deletestock(int id) {
        boolean isSuccess = false;
        try {
            StockEntity stock = stockRepository.findById(id);
            stockRepository.delete(stock);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException("Lỗi delete stock " + e.getMessage());
        }
        return isSuccess;
    }

}
