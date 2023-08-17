package com.teamwork.boutique.service.imp;

import com.teamwork.boutique.payload.request.CartDeleteByIdsRequest;
import com.teamwork.boutique.payload.request.CartUpdateRequest;
import com.teamwork.boutique.payload.response.CartResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface CartServiceImp {
    boolean addToCart(int productId, int colorId, int quantity, int userId);

    List<CartResponse> getAllCart(int userId);

    boolean delete(int cartId);
    boolean deleteByIds(Set<CartDeleteByIdsRequest> ids);

    int countCartItems(int userId);
    boolean updateCart(int id, int quantity);
}