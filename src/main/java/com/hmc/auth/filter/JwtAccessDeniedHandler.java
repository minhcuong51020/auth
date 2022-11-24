package com.hmc.auth.filter;

import com.hmc.common.enums.error.AuthenticationError;
import com.hmc.common.enums.error.AuthorizationError;
import com.hmc.common.exception.ResponseException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
            throws IOException{
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        OutputStream outputStream = response.getOutputStream();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(outputStream, httpResponse);
//        outputStream.flush();
        throw new ResponseException(AuthorizationError.USER_DO_NOT_HAVE_ROLE);
    }
}
