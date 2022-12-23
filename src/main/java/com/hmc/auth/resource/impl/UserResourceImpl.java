package com.hmc.auth.resource.impl;

import com.hmc.auth.dto.request.LoginRequest;
import com.hmc.auth.dto.request.RegisterRequest;
import com.hmc.auth.dto.response.TokenResponse;
import com.hmc.auth.entity.User;
import com.hmc.auth.entity.UserPrincipal;
import com.hmc.auth.resource.UserResource;
import com.hmc.auth.service.UserService;
import com.hmc.common.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserResourceImpl implements UserResource {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @Override
    public Response<TokenResponse> login(LoginRequest request) {
        this.authentication(request.getUsername(), request.getPassword());
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println(userDetails.getUsername());
        }
        return Response.of(this.userService.login(request));
    }

    @Override
    public Response<User> register(RegisterRequest request) {
        return Response.of(this.userService.register(request));
    }

    @Override
    public String test() {
        return "OK";
    }

    private void authentication(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = this.authenticationManager.authenticate(token);
        if(auth.isAuthenticated()) {
            System.out.println(auth.getPrincipal());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

}
