package com.teamwork.boutique.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamwork.boutique.entity.StockEntity;

public class CartResponse {

    private int id;
    @JsonIgnore
    private StockEntity stock;
    private int quantity;
    private double stockPrice;
    private String productName;
    private String stockImage;
    private int stockId;
    private int productId;
    private int maxQuantity;

    public int getMaxQuantity() {
        return maxQuantity;
    }
    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public String getStockImage() {
        return stockImage;
    }

    public void setStockImage(String stockImage) {
        this.stockImage = stockImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StockEntity getStock() {
        return stock;
    }

    public void setStock(StockEntity stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}