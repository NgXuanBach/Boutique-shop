package com.teamwork.boutique.payload.request;

public class AddressSaveRequest {
    private int userId;
    private int countryId;
    private int cityProvinceId;
    private String detail;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getCityProvinceId() {
        return cityProvinceId;
    }

    public void setCityProvinceId(int cityProvinceId) {
        this.cityProvinceId = cityProvinceId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public AddressSaveRequest() {
    }
}
