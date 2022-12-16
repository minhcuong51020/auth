package com.hmc.auth.service.impl;

import com.hmc.auth.dto.request.LoginRequest;
import com.hmc.auth.dto.request.RegisterRequest;
import com.hmc.auth.dto.response.TokenResponse;
import com.hmc.auth.dto.response.UserResponse;
import com.hmc.auth.entity.Role;
import com.hmc.auth.entity.User;
import com.hmc.auth.entity.UserPrincipal;
import com.hmc.auth.repository.RoleRepository;
import com.hmc.auth.repository.UserRepository;
import com.hmc.auth.service.UserService;
import com.hmc.auth.support.RoleEnum;
import com.hmc.auth.utility.JWTTokenProvider;
import com.hmc.common.enums.error.AuthenticationError;
import com.hmc.common.exception.ResponseException;
import com.hmc.common.util.IdUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final JWTTokenProvider jwtTokenProvider;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public TokenResponse login(LoginRequest request) {
        User user = this.findUserByUsername(request.getUsername());
        Role role = this.roleRepository.findById(user.getRoleId()).orElseThrow(
                () ->  new ResponseException(AuthenticationError.AUTHENTICATION_ERROR)
        );
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        UserPrincipal userPrincipal = new UserPrincipal(user, authorities);
        String accessToken = jwtTokenProvider.generateJwtToken(userPrincipal);
        String refreshToken = "";
        TokenResponse tokenResponse = new TokenResponse(accessToken, refreshToken, user.getId(), user.getFullName());
        return tokenResponse;
    }

    @Override
    public User register(RegisterRequest request) {
        Role role = roleRepository.findRoleByName(RoleEnum.USER.getName());
        User user = new User();
        user.setId(IdUtils.nextId());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setFullName(request.getFullName());
        user.setRoleId(role.getId());
        this.userRepository.save(user);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = this.userRepository.findUserByUsername(username);
        if(user == null) {
            throw new ResponseException(AuthenticationError.AUTHENTICATION_ERROR);
        }
        return user;
    }

    @Override
    public List<UserResponse> findAllUser() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);
        Role role = this.roleRepository.findById(user.getRoleId()).orElseThrow(
                () ->  new ResponseException(AuthenticationError.AUTHENTICATION_ERROR)
        );
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        UserPrincipal userPrincipal = new UserPrincipal(user, authorities);
        return userPrincipal;
    }

}
