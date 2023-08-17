package com.teamwork.boutique.controller;

import com.google.gson.Gson;
import com.teamwork.boutique.payload.request.StockRequest;
import com.teamwork.boutique.payload.response.BaseResponse;
import com.teamwork.boutique.service.imp.StockServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {
    private Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(StockController.class);
    @Autowired
    private StockServiceImp stockServiceImp;
    @CrossOrigin(value = "*")
    @GetMapping("/product")
    public ResponseEntity<?> getStockByProductId(@RequestParam int id){
        logger.info("request:"+id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(stockServiceImp.getStockByProductId(id));
        baseResponse.setMessage("List Stock By ProDuct Id");
        baseResponse.setStatusCode(200);
        logger.info("response:"+gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllCategory(){
        logger.info("request:none");
        BaseResponse response=new BaseResponse();
        response.setStatusCode(200);
        response.setData(stockServiceImp.getAllStock());
        logger.info("response:"+gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addStock(int colorId, int quantity, int productId, double price, String imageUrl) {
        logger.info("request colorId" + colorId+" quantity "+quantity+" productId "+productId
                +" price "+price +"imageUrl"+ imageUrl);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(stockServiceImp.addStock(colorId,quantity,productId,price,imageUrl));
        logger.info("response:"+gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseEntity<?> updateStock(StockRequest request) {
        logger.info("Request :"+gson.toJson(request));
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(stockServiceImp.updateStock(request));
        baseResponse.setMessage("update stock");
        baseResponse.setStatusCode(200);
        logger.info("response:"+gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @GetMapping("/delete")
    public ResponseEntity<?> deletestock(int id) {
        logger.info("request:id "+id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(stockServiceImp.deletestock(id));
        logger.info("response:"+gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
