package com.teamwork.boutique.payload.response;

import java.util.List;

public class DetailResponse {
    private String name;
    private int productId;
    private String description;
    private int categoryId;
    private String categoryName;
    private List<ReviewResponse> reviewList;

    private List<StockResponse> stockResponseList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StockResponse> getStockResponseList() {
        return stockResponseList;
    }

    public void setStockResponseList(List<StockResponse> stockResponseList) {
        this.stockResponseList = stockResponseList;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int category) {
        this.categoryId = category;
    }


    public List<ReviewResponse> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewResponse> reviewList) {
        this.reviewList = reviewList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
