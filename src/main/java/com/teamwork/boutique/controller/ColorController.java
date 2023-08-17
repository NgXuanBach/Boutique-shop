package com.teamwork.boutique.controller;

import com.google.gson.Gson;
import com.teamwork.boutique.payload.response.BaseResponse;
import com.teamwork.boutique.service.imp.ColorServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/color")
@CrossOrigin("*")
public class ColorController {
    @Autowired
    ColorServiceImp colorServiceImp;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private Gson gson = new Gson();

    @GetMapping("")
    public ResponseEntity<?> getAllColor() {
        logger.info("Request :none");
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(colorServiceImp.getAllColor());
        logger.info("Response :"+new Gson().toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addColor(String colorName) {
        logger.info("Request : colorName :"+colorName);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(colorServiceImp.addColor(colorName));
        logger.info("Response :"+new Gson().toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteColor(int id) {
        logger.info("Request : colorId :"+id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(colorServiceImp.deleteColor(id));
        logger.info("Response :"+new Gson().toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
