package com.teamwork.boutique.payload.request;

public class CartDeleteByIdsRequest {
    private int cartId;

    public CartDeleteByIdsRequest() {
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
}
