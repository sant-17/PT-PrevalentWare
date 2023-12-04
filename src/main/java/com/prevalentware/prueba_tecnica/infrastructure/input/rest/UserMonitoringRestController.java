package com.prevalentware.prueba_tecnica.infrastructure.input.rest;

import com.prevalentware.prueba_tecnica.application.dto.request.UserMonitoringRequestDto;
import com.prevalentware.prueba_tecnica.application.dto.response.UserMonitoringResponseDto;
import com.prevalentware.prueba_tecnica.application.service.IUserMonitoringService;
import com.prevalentware.prueba_tecnica.infrastructure.utils.APIResponse;
import com.prevalentware.prueba_tecnica.infrastructure.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-monitoring")
@RequiredArgsConstructor
public class UserMonitoringRestController {
    private final IUserMonitoringService userMonitoringService;

    @GetMapping("/")
    public ResponseEntity<APIResponse<List<UserMonitoringResponseDto>>> getUserMonitoring(@RequestBody UserMonitoringRequestDto dto){
        List<UserMonitoringResponseDto> userMonitoringResponseDtoList = userMonitoringService.getUserMonitoringByEmail(
                dto.getEmail(),
                dto.getFrom(),
                dto.getTo()
        );

        APIResponse<List<UserMonitoringResponseDto>> response = APIResponse.ok(
                userMonitoringResponseDtoList,
                Constant.getLogResponseHashMap(),
                Constant.LOG_RESPONSE_CODE_PREFIX.concat("6")
        );

        return ResponseEntity
                .status(HttpStatus.valueOf(response.getHttpStatus()))
                .body(response);
    }
}
