package com.teamwork.boutique.service.imp;


import com.teamwork.boutique.payload.request.ChangePasswordRequest;
import com.teamwork.boutique.payload.request.FindUserIdRequest;
import com.teamwork.boutique.payload.request.SignupRequest;
import com.teamwork.boutique.payload.response.LoginSigupResponse;
import com.teamwork.boutique.payload.response.UserResponse;

import java.util.List;

public interface UserServiceImp {
    LoginSigupResponse addUser(SignupRequest request);

    boolean deleteUser(int userId);

    int findUserId(FindUserIdRequest request);

    boolean updateUserRole(int userId, int roleId);

    List<UserResponse> getAllUser();

    int getUserIdByToken(String token);
    boolean changePassword(ChangePasswordRequest request);
    UserResponse getUserByToken(String token);
    int getRoleIdByToken(String request);
}
