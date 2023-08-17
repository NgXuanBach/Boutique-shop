package com.teamwork.boutique.controller;

import com.google.gson.Gson;
import com.teamwork.boutique.payload.response.BaseResponse;
import com.teamwork.boutique.service.imp.CityProvinceServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/city-province")
public class CityProvinceController {
    @Autowired
    private CityProvinceServiceImp cityProvinceServiceImp;
    private Logger logger = LoggerFactory.getLogger(CityProvinceController.class);
    @GetMapping("/clear-cache")
    @CacheEvict(value = "listCityProvince", allEntries = true)
    public ResponseEntity<?> clearCache() {
        logger.info("Request :none");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData("");
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("clear cache list city province");
        logger.info("Response:"+ new Gson().toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        logger.info("Request :none");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("List City/Province");
        baseResponse.setData(cityProvinceServiceImp.getAll());
        logger.info("Response:"+ new Gson().toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
