package com.teamwork.boutique.service.imp;

import com.teamwork.boutique.payload.request.ProductRequest;
import com.teamwork.boutique.payload.response.DetailResponse;
import com.teamwork.boutique.payload.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductServiceImp {
    List<ProductResponse> getAllProduct();
    Object getProductByCategory(int id);
    DetailResponse getDetailProductByProductId(int productId);
    boolean saveProduct(ProductRequest request);
    boolean deleteProduct(int id);

    List<ProductResponse> getBestSellerProduct();

}
