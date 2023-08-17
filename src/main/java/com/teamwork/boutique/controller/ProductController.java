package com.teamwork.boutique.controller;

import com.google.gson.Gson;
import com.teamwork.boutique.payload.request.ProductRequest;
import com.teamwork.boutique.payload.response.BaseResponse;
import com.teamwork.boutique.service.imp.ProductServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductServiceImp productServiceImp;
    private Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        logger.info("request :none");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(productServiceImp.getAllProduct());
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("List Product");
        logger.info("response" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detailProduct(@RequestParam int id) {
        logger.info("request :id "+id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(productServiceImp.getDetailProductByProductId(id));
        baseResponse.setMessage("Detail by product id");
        baseResponse.setStatusCode(200);
        logger.info("response" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> saveProduct(ProductRequest request) {
        logger.info("Request :"+gson.toJson(request));
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(productServiceImp.saveProduct(request));
        baseResponse.setMessage("add product");
        baseResponse.setStatusCode(200);
        logger.info("response" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteProduct(int id) {
        logger.info("request :id "+id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(productServiceImp.deleteProduct(id));
        logger.info("response" + gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/bestseller")
    public ResponseEntity<?> getBestSellerProduct() {
        logger.info("request :none");
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(productServiceImp.getBestSellerProduct());
        logger.info("response" + gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
