package com.teamwork.boutique.payload.response;

public class AddressResponse {
    private int id;
    private String cityOrProvinceName;
    private String countryName;
    private double fee;
    private String detail;

    public AddressResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityOrProvinceName() {
        return cityOrProvinceName;
    }

    public void setCityOrProvinceName(String cityOrProvinceName) {
        this.cityOrProvinceName = cityOrProvinceName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
