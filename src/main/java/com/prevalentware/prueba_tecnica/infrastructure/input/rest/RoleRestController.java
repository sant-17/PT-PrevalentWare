package com.prevalentware.prueba_tecnica.infrastructure.input.rest;

import com.prevalentware.prueba_tecnica.application.dto.response.RoleResponseDto;
import com.prevalentware.prueba_tecnica.application.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleRestController {

    private final IRoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles(){
        return ResponseEntity
                .ok()
                .body(roleService.getAllRoles());
    }
}
