package com.hmc.auth.filter;

import com.hmc.common.enums.error.AuthenticationError;
import com.hmc.common.exception.ResponseException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException exception) throws IOException {
//        HttpResponse httpResponse = new HttpResponse(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN,
//                                        HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(),
//                                        SecurityConstant.FORBIDDEN_MESSAGE);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setStatus(HttpStatus.FORBIDDEN.value());
//        OutputStream outputStream = response.getOutputStream();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(outputStream, httpResponse);
//        outputStream.flush();
        throw new ResponseException(AuthenticationError.AUTHENTICATION_ERROR);
    }
}
