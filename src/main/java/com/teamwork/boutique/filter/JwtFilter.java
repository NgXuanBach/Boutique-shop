package com.teamwork.boutique.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamwork.boutique.entity.UserEntity;
import com.teamwork.boutique.exception.CustomException;
import com.teamwork.boutique.payload.response.BaseResponse;
import com.teamwork.boutique.repository.UserRepository;
import com.teamwork.boutique.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

//Tất cả request đều phải chạy filter này
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserRepository userRepository;

    /**
     * Nhận được token truyền trên header
     * Giải mã token
     * Nếu giải mã thành công thì hợp lệ
     * Tạo chứng thực và cho đi vào link người dùng gọi
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            //lấy giá trị của heaader có key là Authorization
            String header = request.getHeader("Authorization");
            System.out.println("check header :" + header);
            if (header != null && header.startsWith("Bearer ")) {

// Cắt bỏ chuỗi Bearer và lấy ra token
                String token = header.substring(7);
//  Giải mã token
                Claims claims = null;
                try {
                    claims = jwtHelper.decodeToken(token);
                }catch (Exception e){

                }
                if (claims != null) {
                    // Tạo chứng thực cho security
                    UserEntity userEntity = userRepository.findByEmail(claims.getSubject());
                    ArrayList<GrantedAuthority> roleList = new ArrayList<>();
                    roleList.add(new SimpleGrantedAuthority(userEntity.getRole().getName()));
                    System.out.println("check role :" + roleList.get(0));
                    if (userEntity != null) {
//                        request.setAttribute("userId",userEntity.getId());
                        SecurityContext context = SecurityContextHolder.getContext();
                        UsernamePasswordAuthenticationToken user =
                                new UsernamePasswordAuthenticationToken(userEntity.getEmail(), "", roleList);
                        context.setAuthentication(user);
                    } else {
                        returnBaseReponseEntity(response, "User with" + claims.getSubject() + " is null", 500);
                    }

                }
            }
        } catch (CustomException e) {
            returnBaseReponseEntity(response, e.getMessage(), 500);
        }
        filterChain.doFilter(request, response);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            boolean isAuthenticated = authentication.isAuthenticated();
            String principalName = authentication.getName();
            String authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));
            System.out.println("Authentication result - Authenticated: " + isAuthenticated + ", User: " + principalName + ", Authorities: " + authorities);
        } else {
            System.out.println("Authentication result - Not authenticated");
        }

    }

    private void returnBaseReponseEntity(HttpServletResponse response, String message, int statusCode) throws IOException {
        BaseResponse baseResponse = new BaseResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        baseResponse.setMessage(message);
        baseResponse.setStatusCode(statusCode);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), baseResponse);
        return;
    }
}
