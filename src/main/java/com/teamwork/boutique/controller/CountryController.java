package com.teamwork.boutique.controller;

import com.google.gson.Gson;
import com.teamwork.boutique.payload.response.BaseResponse;
import com.teamwork.boutique.service.imp.CountryServiceImp;
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
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryServiceImp countryServiceImp;
    private Logger logger = LoggerFactory.getLogger(CountryController.class);
    @GetMapping("/clear-cache")
    @CacheEvict(value = "listCountry", allEntries = true)
    public ResponseEntity<?> clearCache() {
        logger.info("Request :none");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData("");
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("clear cache list country");
        logger.info("Response:"+new Gson().toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        logger.info("Request :none");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("List Country");
        baseResponse.setStatusCode(200);
        baseResponse.setData(countryServiceImp.getAll());
        logger.info("Response:"+new Gson().toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
