package com.teamwork.boutique.provider;

import com.teamwork.boutique.entity.UserEntity;
import com.teamwork.boutique.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomAuthenProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy // quét sau cùng để nó config bên securityconfig trước rồi mình lấy sau
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //lấy email
        String email = authentication.getName();
        //lấy password
        String password = authentication.getCredentials().toString();
        UserEntity user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            //đăng nhập thành công
            return new UsernamePasswordAuthenticationToken(email, user.getPassword(), new ArrayList<>());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //khai báo loại chứng thực sử dụng để so sánh
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
