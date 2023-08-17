package com.teamwork.boutique.service.imp;

import com.teamwork.boutique.payload.response.ColorResponse;

import java.util.List;

public interface ColorServiceImp {
    List<ColorResponse> getAllColor();

    String addColor(String colorName);

    boolean deleteColor(int id);
}
