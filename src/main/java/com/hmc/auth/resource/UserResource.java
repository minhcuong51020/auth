package com.hmc.auth.resource;

import com.hmc.auth.dto.request.LoginRequest;
import com.hmc.auth.dto.request.RegisterRequest;
import com.hmc.auth.dto.response.TokenResponse;
import com.hmc.auth.entity.User;
import com.hmc.common.dto.response.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/iam/api")
public interface UserResource {

    @PostMapping("/login")
    Response<TokenResponse> login(@RequestBody LoginRequest request);

    @PostMapping("/register")
    Response<User> register(@RequestBody RegisterRequest request);

    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String test();

}
