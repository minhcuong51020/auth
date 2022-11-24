package com.hmc.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    private String id;

    private String fullName;

    private String email;

    private String phone;

    private RoleResponse role;

}
