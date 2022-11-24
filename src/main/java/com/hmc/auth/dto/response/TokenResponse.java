package com.hmc.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse implements Serializable {

    private String accessToken;

    private String refreshToken;

    private String id;

    private String fullName;


}
