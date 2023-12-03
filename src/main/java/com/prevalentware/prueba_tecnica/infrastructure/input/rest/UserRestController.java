package com.prevalentware.prueba_tecnica.infrastructure.input.rest;

import com.prevalentware.prueba_tecnica.application.dto.response.UserResponseDto;
import com.prevalentware.prueba_tecnica.application.service.IUserService;
import com.prevalentware.prueba_tecnica.infrastructure.utils.APIResponse;
import com.prevalentware.prueba_tecnica.infrastructure.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserService userService;

    @GetMapping("/all/size/{size}/number/{number}")
    public ResponseEntity<APIResponse<List<UserResponseDto>>> getAllUsers(@PathVariable("size") Integer size, @PathVariable("number") Integer number){
        List<UserResponseDto> userResponseDtoList = userService.getAllUsers(number, size);

        APIResponse<List<UserResponseDto>> response = APIResponse.ok(
                userResponseDtoList,
                Constant.getLogResponseHashMap(),
                Constant.LOG_RESPONSE_CODE_PREFIX.concat("6")
        );

        return ResponseEntity
                .status(HttpStatus.valueOf(response.getHttpStatus()))
                .body(response);
    }

    @GetMapping("/{email}")
    public ResponseEntity<APIResponse<UserResponseDto>> getUserByEmail(@PathVariable("email") String email){
        UserResponseDto userResponseDto = userService.getUserByEmail(email);

        APIResponse<UserResponseDto> response = APIResponse.ok(
                userResponseDto,
                Constant.getLogResponseHashMap(),
                Constant.LOG_RESPONSE_CODE_PREFIX.concat("6")
        );

        return ResponseEntity
                .status(HttpStatus.valueOf(response.getHttpStatus()))
                .body(response);
    }
}
