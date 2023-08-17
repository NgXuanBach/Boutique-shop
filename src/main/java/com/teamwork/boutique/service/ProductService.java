package com.teamwork.boutique.service;

import com.teamwork.boutique.entity.ProductEntity;
import com.teamwork.boutique.entity.ReviewEntity;
import com.teamwork.boutique.entity.StockEntity;
import com.teamwork.boutique.entity.*;
import com.teamwork.boutique.exception.CustomException;
import com.teamwork.boutique.payload.request.ProductRequest;
import com.teamwork.boutique.payload.response.*;
import com.teamwork.boutique.repository.CategoryRepository;
import com.teamwork.boutique.repository.ProductRepository;
import com.teamwork.boutique.repository.StockRepository;
import com.teamwork.boutique.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceImp {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (ProductEntity item : productRepository.findAll()) {
            ProductResponse response = new ProductResponse();
            response.setId(item.getId());
            response.setName(item.getName());
            response.setImage(item.getImage());
            double minPrice = 0;
            try {
                minPrice = stockRepository.findMinPriceByProductId(item.getId());
            } catch (Exception e) {
            }
            response.setPrice(minPrice);
            response.setDescription(item.getDesc());
            response.setCategoryId(item.getCategory().getId());
            response.setSoldQuantity(item.getSoldQuantity());
            productResponses.add(response);
        }
        return productResponses;
    }

    @Override
    public List<ProductResponse> getProductByCategory(int id) {
        List<ProductEntity> list = productRepository.findByCategoryId(id);
        List<ProductResponse> responseList = new ArrayList<>();
        for (ProductEntity data : list
        ) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(data.getId());
            productResponse.setName(data.getName());
            productResponse.setImage(data.getImage());
            productResponse.setPrice(stockRepository.findMinPriceByProductId(data.getId()));
            productResponse.setDescription(data.getDesc());
            productResponse.setCategoryId(data.getCategory().getId());
            productResponse.setSoldQuantity(data.getSoldQuantity());
            responseList.add(productResponse);
        }
        return responseList;
    }

    @Override
    public DetailResponse getDetailProductByProductId(int productId) {
        DetailResponse detailResponse = new DetailResponse();
        try {
            ProductEntity item = productRepository.findById(productId);
            detailResponse.setName(item.getName());
            detailResponse.setProductId(item.getId());
            detailResponse.setCategoryId(item.getCategory().getId());
            detailResponse.setCategoryName(item.getCategory().getName());
            detailResponse.setDescription(item.getDesc());
//        set reviewList for detail
            List<ReviewResponse> reviewList = new ArrayList<>();
            for (ReviewEntity review : item.getReviews()) {
                ReviewResponse reviewResponse = new ReviewResponse(item.getId(), review.getUser().getUsername(), review.getContent(), review.getAppriciation());
                reviewList.add(reviewResponse);
            }
            detailResponse.setReviewList(reviewList);
//        Set stockList for detail
            List<StockResponse> stockResponseList = new ArrayList<>();
            for (StockEntity stock : item.getStocks()) {
                StockResponse stockResponse = new StockResponse();
                stockResponse.setId(stock.getId());
                stockResponse.setQuantity(stock.getQuantity());
                stockResponse.setImage(stock.getImage());
                stockResponse.setPrice(stock.getPrice());
                stockResponse.setColorId(stock.getColor().getId());
                stockResponse.setColorName(stock.getColor().getName());
                stockResponseList.add(stockResponse);
            }
            detailResponse.setStockResponseList(stockResponseList);
        } catch (Exception e) {
            throw new CustomException("Lỗi getDetailProductByProductId");
        }
        return detailResponse;
    }

    @Override
    public boolean saveProduct(ProductRequest request) {
        boolean isSuccess = false;
        try {
            System.out.println("da thuc hien lenh");
            ProductEntity product = new ProductEntity();
            try {
                product.setId(request.getId());
            } catch (Exception e) {

            }
            product.setName(request.getName());
            product.setImage(request.getImage());
            product.setDesc(request.getDesc());
            CategoryEntity category = categoryRepository.findById(request.getCategoryId());
            product.setCategory(category);
            product.setSoldQuantity(request.getSoldQuantity());
            productRepository.save(product);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return isSuccess;

    }

    @Override
    public boolean deleteProduct(int id) {
        boolean isSuccess = false;
        try {
            ProductEntity product = productRepository.findById(id);
            productRepository.delete(product);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException("Lỗi delete product " + e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public List<ProductResponse> getBestSellerProduct() {
        List<ProductResponse> listBestSeller = getAllProduct();
        try {
            listBestSeller.sort((o1, o2)
                    -> o2.getSoldQuantity() - o1.getSoldQuantity());
        } catch (Exception e) {
            throw new CustomException("Lỗi getBestSellerProduct");
        }
        return listBestSeller;
    }

}
