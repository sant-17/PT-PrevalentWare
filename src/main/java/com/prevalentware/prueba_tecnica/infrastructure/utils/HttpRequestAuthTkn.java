package com.prevalentware.prueba_tecnica.infrastructure.utils;

import com.prevalentware.prueba_tecnica.domain.exception.RoleUnauthorizedException;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository.IRoleRepository;
import com.prevalentware.prueba_tecnica.infrastructure.security.DecodedToken;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class HttpRequestAuthTkn {
    private final IRoleRepository roleRepository;
    public String getRoleFromHttpRequest(HttpServletRequest request) throws UnsupportedEncodingException {
        String token = request.getHeader("Authorization");
        String role = "";
        if (token != null && token.startsWith("Bearer ")) {
            DecodedToken decodedToken = DecodedToken.getDecodedToken(token);
            role = decodedToken.getRole();
            role = roleRepository.findRoleById(role).getName().toString();
        }
        return role;
    }

    public String getEmailFromHttpRequest(HttpServletRequest request) throws UnsupportedEncodingException {
        String token = request.getHeader("Authorization");
        String email = "";
        if (token != null && token.startsWith("Bearer ")) {
            DecodedToken decodedToken = DecodedToken.getDecodedToken(token);
            email = decodedToken.getSub();
        }
        return email;
    }

    public void adminAuthorization(String role) {
        if (!Objects.equals(role, "Admin")){
            throw new RoleUnauthorizedException("Only Admin users can access this endpoint");
        }
    }

    public void managerAuthorization(String role) {
        if (!Objects.equals(role, "Admin") && !Objects.equals(role, "Manager")){
            throw new RoleUnauthorizedException("Only Admin and Manager users can access this endpoint");
        }
    }

    public void userAuthorization(String role) {
        if (!Objects.equals(role, "Admin") && !Objects.equals(role, "Manager") && !Objects.equals(role, "User")){
            throw new RoleUnauthorizedException("Only Admin, Manager and Users can access this endpoint");
        }
    }
}
