package com.hmc.auth.service;

import com.hmc.auth.dto.request.LoginRequest;
import com.hmc.auth.dto.request.RegisterRequest;
import com.hmc.auth.dto.response.TokenResponse;
import com.hmc.auth.dto.response.UserResponse;
import com.hmc.auth.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface UserService extends UserDetailsService {

    TokenResponse login(LoginRequest request);

    User register(RegisterRequest request);

    User findUserByUsername(String username);

    List<UserResponse> findAllUser();

}
