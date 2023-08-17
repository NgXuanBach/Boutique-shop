package com.teamwork.boutique.controller;

import com.google.gson.Gson;
import com.teamwork.boutique.exception.CustomException;
import com.teamwork.boutique.payload.request.OrderDetailSaveRequest;
import com.teamwork.boutique.payload.response.BaseResponse;
import com.teamwork.boutique.service.imp.OrderDetailServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/order-detail")
public class OrderDetailController {
    @Autowired
    private OrderDetailServiceImp orderDetailServiceImp;
    private Logger logger = LoggerFactory.getLogger(OrderDetailController.class);
    private Gson gson = new Gson();
    @GetMapping("/user")
    public ResponseEntity<?> getByUserId(@RequestParam int id){
        logger.info("Request :"+id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(orderDetailServiceImp.getByUserId(id));
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("List Product Ordered By User Id ");
        logger.info("Response :"+gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    };
    @PostMapping("/save")
    public ResponseEntity<?> save (@RequestBody List<OrderDetailSaveRequest> request, BindingResult result){
        logger.info("Request :"+gson.toJson(request));
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Saved List Order Detail");
        baseResponse.setData("");
        orderDetailServiceImp.save(request);
        logger.info("Response :"+gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
    @GetMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam int id){
        logger.info("Request :"+id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Deleted order detail");
        baseResponse.setStatusCode(200);
        orderDetailServiceImp.delete(id);
        baseResponse.setData("");
        logger.info("Response :"+gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
