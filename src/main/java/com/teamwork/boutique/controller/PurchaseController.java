package com.teamwork.boutique.controller;

import com.google.gson.Gson;
import com.teamwork.boutique.exception.CustomException;
import com.teamwork.boutique.payload.request.ReviewRequest;
import com.teamwork.boutique.payload.response.BaseResponse;
import com.teamwork.boutique.service.imp.PurchaseServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/purchase")
@CrossOrigin("*")
public class PurchaseController {
    @Autowired
    PurchaseServiceImp purchaseServiceImp;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private Gson gson = new Gson();

    @PostMapping("/rate")
    public ResponseEntity<?> rate(  @Valid  ReviewRequest reviewRequest, BindingResult result) {
        logger.info("Request :" + gson.toJson(reviewRequest));
        List<FieldError> list = result.getFieldErrors();
        for (FieldError data :
                list) {
            throw new CustomException(data.getDefaultMessage());
        }
        boolean isSuccess = purchaseServiceImp.addReview(reviewRequest);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(isSuccess);
        logger.info("Response :"+new Gson().toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
