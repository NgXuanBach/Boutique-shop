package com.teamwork.boutique.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.teamwork.boutique.entity.CategoryEntity;
import com.teamwork.boutique.entity.ColorEntity;
import com.teamwork.boutique.exception.CustomException;
import com.teamwork.boutique.payload.request.CategorySaveRequest;
import com.teamwork.boutique.payload.response.CategoryRespone;
import com.teamwork.boutique.payload.response.ColorResponse;
import com.teamwork.boutique.repository.CategoryRepository;
import com.teamwork.boutique.repository.ColorRepository;
import com.teamwork.boutique.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryRespone> getAllCategory() {
        List<CategoryRespone>responselist=new ArrayList<>();
        List<CategoryEntity> list =categoryRepository.findAll();


        for(CategoryEntity data: list) {
            CategoryRespone categoryRespone = new CategoryRespone();
            categoryRespone.setId(data.getId());
            categoryRespone.setName(data.getName());
            responselist.add(categoryRespone);
        }
        return responselist;
    }

    //    @Override
//    public boolean createCate(CategorySaveRequest request) {
//        boolean isSuccess=false;
//        try {
//            CategoryEntity category=new CategoryEntity();
//
//            category.setName(request.getCategoryName());
//            categoryRepository.save(category);
//            isSuccess=true;
//        }catch (Exception e) {
//
//        }
//
//        return isSuccess;
//    }
    @Override
    public String createCate(String catename) {
        String message = "";
        boolean check = true;
        if (catename != null && !catename.isEmpty()) {
            for (CategoryRespone categoryResponse : getAllCategory()) {
                if (categoryResponse.getName().equalsIgnoreCase(catename)) {
                    message = "This category name have existed already. Please type another name !";
                    check = false;
                }
            }
            if (check) {
                CategoryEntity category = new CategoryEntity();
                category.setName(catename);
                categoryRepository.save(category);
                message = "Save successfully !";
            }
        } else {
            message = "You have not type category yet. Please type category before click Create button!";
        }
        return message;
    }

    @Override
    public boolean deletecate(int id) {
        boolean isSuccess = false;
        try {
            CategoryEntity category = categoryRepository.findById(id);
            categoryRepository.delete(category);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException("Lỗi delete category " + e.getMessage());
        }
        return isSuccess;    }

//    @Override
//    public boolean deleteCate(CategorySaveRequest request) {
//        boolean isSuccess=false;
//        try {
//            CategoryEntity category=new CategoryEntity();
//            category.setId(request.getCategoryId());
//            categoryRepository.delete(category);
//            isSuccess=true;
//        }catch (Exception e) {
//
//        }
//
//        return isSuccess;
//    }
//
//    @Override
//    public boolean updateCate(CategorySaveRequest request) {
//        boolean isSuccess=false;
//        try {
//            CategoryEntity category=new CategoryEntity();
//            category.setId(request.getCategoryId());
//            category.setName(request.getCategoryName());
//            categoryRepository.save(category);
//            isSuccess=true;
//        }catch (Exception e) {
//
//        }
//
//        return isSuccess;    }
}