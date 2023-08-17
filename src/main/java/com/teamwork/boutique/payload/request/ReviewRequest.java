package com.teamwork.boutique.payload.request;

import com.teamwork.boutique.entity.ProductEntity;
import com.teamwork.boutique.entity.UserEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class ReviewRequest {
    private String content;

    @NotNull(message = "starnumber not null")
//    @NotEmpty(message = "starnumber not null")
    @Min(value = 0, message = "starnumber must be greater than or equal to 1")
    @Max(value = 5, message = "starnumber must be greater less or equal to 5")
    private int starNumber;

    @NotNull(message = "userid not null")
    private int  userId;
    @NotNull(message = "productId not null")
    private int productId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStarNumber() {
        return starNumber;
    }

    public void setStarNumber(int starNumber) {
        this.starNumber = starNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
