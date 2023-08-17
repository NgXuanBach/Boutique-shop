package com.teamwork.boutique.controller;


import com.google.gson.Gson;
import com.teamwork.boutique.payload.response.BaseResponse;
import com.teamwork.boutique.service.imp.CategoryServiceImp;
import com.teamwork.boutique.service.imp.ProductServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(value = "*")

@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ProductServiceImp productServiceImp;
    @Autowired
    private CategoryServiceImp categoryServiceImp;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private Gson gson = new Gson();

    @GetMapping("")
    public ResponseEntity<?> getAllCategory(){
        logger.info("Request:");
        BaseResponse response=new BaseResponse();
        response.setStatusCode(200);
        response.setData(categoryServiceImp.getAllCategory());
        logger.info("Response :"+gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getProductByCategory(@PathVariable int id){
        logger.info("Request:"+id);
        BaseResponse response=new BaseResponse();
        response.setStatusCode(200);
        response.setData(productServiceImp.getProductByCategory(id));
        logger.info("Response :"+gson.toJson(response));
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createcate(String catename) {
        logger.info("Request:"+catename);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(categoryServiceImp.createCate(catename));
        logger.info("Response :"+gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/delete")
    public ResponseEntity<?> deleteCategory(int id) {
        logger.info("Request:"+id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(categoryServiceImp.deletecate(id));
        logger.info("Response :"+gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}