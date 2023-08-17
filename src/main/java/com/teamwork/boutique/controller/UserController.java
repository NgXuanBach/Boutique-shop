package com.teamwork.boutique.controller;

import com.google.gson.Gson;
import com.teamwork.boutique.exception.CustomException;
import com.teamwork.boutique.payload.request.ChangePasswordRequest;
import com.teamwork.boutique.payload.request.SignupRequest;
import com.teamwork.boutique.payload.response.BaseResponse;
import com.teamwork.boutique.service.imp.UserServiceImp;
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
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private Gson gson = new Gson();

    @GetMapping("/getall")
    public ResponseEntity<?> getAllUser() {
        logger.info("Request :");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.getAllUser());
        baseResponse.setMessage("Get All User");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/updaterole")
    public ResponseEntity<?> updateUserRole(@RequestParam int userId, @RequestParam int roleId) {
        logger.info("Request userId :" + userId + " roleId " + roleId);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.updateUserRole(userId, roleId));
        baseResponse.setMessage("Update user's role");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/getid")
    public ResponseEntity<?> getUserIdByToken(@RequestParam String token) {
        logger.info("Request token :" + token);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.getUserIdByToken(token));
        baseResponse.setMessage("Get user id by token");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/changepass")
    public ResponseEntity<?> changePassword(@Valid ChangePasswordRequest request, BindingResult result) {
        logger.info("Request :" + gson.toJson(request));
        List<FieldError> list = result.getFieldErrors();
        for (FieldError data :
                list) {
            throw new CustomException(data.getDefaultMessage());
        }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.changePassword(request));
        baseResponse.setMessage("change password");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> getUserById(@RequestParam String token) {
        logger.info("Request :" + token);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.getUserByToken(token));
        baseResponse.setMessage("Get username by token");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/getRoleId")
    public ResponseEntity<?> getRoleId(@RequestParam String token) {
        logger.info("Request :" + token);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(userServiceImp.getRoleIdByToken(token));
        response.setMessage("Role id by token.");
        logger.info("Response :" + gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
