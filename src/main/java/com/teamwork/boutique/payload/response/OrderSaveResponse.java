package com.teamwork.boutique.payload.response;

public class OrderSaveResponse {
    private int id;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public OrderSaveResponse() {
    }
}
