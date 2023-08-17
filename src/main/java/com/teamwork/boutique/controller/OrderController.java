package com.teamwork.boutique.controller;

import com.google.gson.Gson;
import com.teamwork.boutique.exception.CustomException;
import com.teamwork.boutique.payload.request.OrderSaveRequest;
import com.teamwork.boutique.payload.response.BaseResponse;
import com.teamwork.boutique.service.imp.OrderServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImp orderServiceImp;
    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    private Gson gson = new Gson();
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody OrderSaveRequest request, BindingResult result){
        logger.info("Request :"+gson.toJson(request));
        if(result.hasErrors()){
            throw new CustomException(result.getObjectName().toString());
        }
        orderServiceImp.save(request);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Saved Order");
        baseResponse.setStatusCode(200);
        baseResponse.setData("");
        logger.info("Response :"+gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
