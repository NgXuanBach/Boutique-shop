package com.teamwork.boutique.controller;

import com.google.gson.Gson;
import com.teamwork.boutique.payload.request.AddressSaveRequest;
import com.teamwork.boutique.payload.request.IdListRequest;
import com.teamwork.boutique.payload.response.BaseResponse;
import com.teamwork.boutique.service.imp.AddressServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressServiceImp addressServiceImp;
    private Logger logger = LoggerFactory.getLogger(AddressController.class);
    private Gson gson = new Gson();
    @GetMapping("/user")
    public ResponseEntity<?> getById(@RequestParam int id){
        logger.info("Request : userId :"+id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("List Address by User ID");
        response.setData(addressServiceImp.getByUserId(id));
        logger.info("Response :"+new Gson().toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AddressSaveRequest request, BindingResult result){
        logger.info("Request :"+gson.toJson(request));
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Saved new address");
        baseResponse.setStatusCode(200);
        baseResponse.setData(addressServiceImp.save(request));
        logger.info("response :"+gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteList(@RequestBody IdListRequest request) {
        logger.info("Request :"+gson.toJson(request));
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Deleted list address.");
        response.setData(addressServiceImp.deleteList(request));
        logger.info("Response :"+gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
