package com.teamwork.boutique.payload.response;

public class ReviewResponse {
    int userId;
    String userName;
    String contentReview;
    int starNumber;

    public ReviewResponse(int userId, String userName, String contentReview, int starNumber) {
        this.userId = userId;
        this.userName = userName;
        this.contentReview = contentReview;
        this.starNumber = starNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContentReview() {
        return contentReview;
    }

    public void setContentReview(String contentReview) {
        this.contentReview = contentReview;
    }

    public int getStarNumber() {
        return starNumber;
    }

    public void setStarNumber(int starNumber) {
        this.starNumber = starNumber;
    }
}
