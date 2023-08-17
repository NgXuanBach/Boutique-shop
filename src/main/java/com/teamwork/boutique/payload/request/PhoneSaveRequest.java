package com.teamwork.boutique.payload.request;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PhoneSaveRequest {
    private int userId;
    private String phoneNumber;

    public PhoneSaveRequest() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
