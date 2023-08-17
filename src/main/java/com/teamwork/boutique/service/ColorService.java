package com.teamwork.boutique.service;

import com.google.gson.Gson;
import com.teamwork.boutique.entity.ColorEntity;
import com.teamwork.boutique.exception.CustomException;
import com.teamwork.boutique.payload.response.ColorResponse;
import com.teamwork.boutique.repository.ColorRepository;
import com.teamwork.boutique.service.imp.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorService implements ColorServiceImp {
    @Autowired
    ColorRepository colorRepository;

    @Override
    public List<ColorResponse> getAllColor() {
        System.out.println("Kiem tra color");
        List<ColorResponse> listResponse = new ArrayList<>();

        List<ColorEntity> list = colorRepository.findAll();
        for (ColorEntity data : list) {
            ColorResponse response = new ColorResponse();
            response.setId(data.getId());
            response.setName(data.getName());
            listResponse.add(response);
        }
        Gson gson = new Gson();
        String data = gson.toJson(listResponse);

        return listResponse;
    }

    @Override
    public String addColor(String colorName) {
        String message = "";
        boolean check = true;
        if (colorName != null && !colorName.isEmpty()) {
            for (ColorResponse colorResponse : getAllColor()) {
                if (colorResponse.getName().equalsIgnoreCase(colorName)) {
                    message = "This color have existed already. Please type another color !";
                    check = false;
                }
            }
            if (check) {
                ColorEntity color = new ColorEntity();
                color.setName(colorName);
                colorRepository.save(color);
                message = "Save successfully !";
            }
        } else {
            message = "You have not type color yet. Please type color before click Create button!";
        }
        return message;
    }

    @Override
    public boolean deleteColor(int id) {
        boolean isSuccess = false;
        try {
            ColorEntity color = colorRepository.findById(id);
            colorRepository.delete(color);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException("Lỗi delete category " + e.getMessage());
        }
        return isSuccess;
    }

}
