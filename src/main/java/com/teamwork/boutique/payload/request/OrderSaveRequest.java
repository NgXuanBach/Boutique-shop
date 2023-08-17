package com.teamwork.boutique.payload.request;

import javax.swing.*;
import java.util.List;

public class OrderSaveRequest {
    private int userId;
    private int addressId;
    private int phoneId;
    private double total;
    private int statusId;
    List<OrderDetailSaveRequest> orderDetailSaveRequests;

    public List<OrderDetailSaveRequest> getOrderDetailSaveRequests() {
        return orderDetailSaveRequests;
    }

    public void setOrderDetailSaveRequests(List<OrderDetailSaveRequest> orderDetailSaveRequests) {
        this.orderDetailSaveRequests = orderDetailSaveRequests;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public OrderSaveRequest() {

    }
}

