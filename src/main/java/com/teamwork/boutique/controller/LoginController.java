package com.teamwork.boutique.controller;

import com.google.gson.Gson;
import com.teamwork.boutique.exception.CustomException;
import com.teamwork.boutique.payload.request.FindUserIdRequest;
import com.teamwork.boutique.payload.request.SignupRequest;
import com.teamwork.boutique.payload.response.BaseResponse;
import com.teamwork.boutique.service.imp.UserServiceImp;
import com.teamwork.boutique.utils.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtHelper jwtHelper;
    @Autowired
    UserServiceImp userServiceImp;
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    private Gson gson = new Gson();

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> signin(@RequestParam String email, @RequestParam String password) {
        logger.info("Request:email "+email+" request: password "+password);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(token);
        String jwt = jwtHelper.generateToken(email);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(jwt);
        logger.info("Response :" + gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest request, BindingResult result) {
        logger.info("Request :" + gson.toJson(request));
        List<FieldError> list = result.getFieldErrors();
        for (FieldError data :
                list) {
            throw new CustomException(data.getDefaultMessage());
        }
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> dataList = new HashMap<>();
        userServiceImp.addUser(request);
        dataList.put("token", jwtHelper.generateToken(request.getEmail()));
        response.setStatusCode(200);
        response.setMessage("Saved user");
        response.setData(dataList);
        logger.info("Response :" + gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/signin/findUserId", method = RequestMethod.POST)
    public ResponseEntity<?> findUserId(FindUserIdRequest request) {
        logger.info("Request :" + gson.toJson(request));
        int id = userServiceImp.findUserId(request);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(id);
        System.out.println(id + " newUserId");
        logger.info("Response :" + gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
