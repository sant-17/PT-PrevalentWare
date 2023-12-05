package com.prevalentware.prueba_tecnica.infrastructure.input.rest;

import com.prevalentware.prueba_tecnica.application.dto.request.TopUsersMonitoringRequestDto;
import com.prevalentware.prueba_tecnica.application.dto.request.UserMonitoringRequestDto;
import com.prevalentware.prueba_tecnica.application.dto.response.TopUsersResponseDto;
import com.prevalentware.prueba_tecnica.application.dto.response.UserMonitoringResponseDto;
import com.prevalentware.prueba_tecnica.application.dto.response.UserResponseDto;
import com.prevalentware.prueba_tecnica.application.service.IUserMonitoringService;
import com.prevalentware.prueba_tecnica.infrastructure.utils.APIResponse;
import com.prevalentware.prueba_tecnica.infrastructure.utils.Constant;
import com.prevalentware.prueba_tecnica.infrastructure.utils.HttpRequestAuthTkn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Tag(name = "UserMonitoring", description = "La API de la tabla UserMonitoring")
@RestController
@RequestMapping("/api/v1/user-monitoring")
@RequiredArgsConstructor
public class UserMonitoringRestController {
    private final IUserMonitoringService userMonitoringService;
    private final HttpRequestAuthTkn httpRequestAuthTkn;

    @Operation(
            summary = "Obtener el monitoreo del usuario.",
            description = "Este endpoint obtiene la información de monitoreo del usuario basado en el correo electrónico " +
                    "y el rango de fechas proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se encontraron los registros de los monitoreos del usuario."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Motivo de este código:" +
                            " - Los parámetros 'from' y 'to' del DTO están en un orden incorrecto. La fecha 'from' debe de ser antes de la fecha 'to'." +
                            " - Los parámetros 'from' y 'to' del DTO están en un formato incorrecto. El formato correcto es yyyy-MM-dd.",
                    content = @Content(schema = @Schema(implementation = APIResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motivo de este código:" +
                            " - No se encontró un Usuario con el email proporcionado." +
                            " - No se encontraron registros de los monitoreos de dicho usuario en las fechas dadas.",
                    content = @Content(schema = @Schema(implementation = APIResponse.class))),
    })
    @GetMapping("/")
    public ResponseEntity<APIResponse<List<UserMonitoringResponseDto>>> getUserMonitoring(@RequestBody @Valid UserMonitoringRequestDto dto, HttpServletRequest request) throws UnsupportedEncodingException {
        httpRequestAuthTkn.adminAuthorization(httpRequestAuthTkn.getRoleFromHttpRequest(request));

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

    @Operation(
            summary = "Obtener los principales usuarios de monitoreo.",
            description = "Este endpoint devuelve una lista de los tres usuarios con más registros en el servicio UserMonitoring, " +
                    "dentro del rango de tiempo especificado.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se encontraron los usuarios principales."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Motivo de este código:" +
                            " - Los parámetros 'from' y 'to' del DTO están en un orden incorrecto. La fecha 'from' debe de ser antes de la fecha 'to'." +
                            " - Los parámetros 'from' y 'to' del DTO están en un formato incorrecto. El formato correcto es yyyy-MM-dd.",
                    content = @Content(schema = @Schema(implementation = APIResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontraron registros de los usuarios en las fechas dadas.",
                    content = @Content(schema = @Schema(implementation = APIResponse.class))),
    })
    @GetMapping("/top-users")
    public ResponseEntity<APIResponse<List<TopUsersResponseDto>>> getTopUsers(@RequestBody @Valid TopUsersMonitoringRequestDto dto, HttpServletRequest request) throws UnsupportedEncodingException {
        httpRequestAuthTkn.adminAuthorization(httpRequestAuthTkn.getRoleFromHttpRequest(request));

        List<TopUsersResponseDto> userResponseDtoList = userMonitoringService.getTopUsersByMonitoring(
                dto.getFrom(),
                dto.getTo()
        );

        APIResponse<List<TopUsersResponseDto>> response = APIResponse.ok(
                userResponseDtoList,
                Constant.getLogResponseHashMap(),
                Constant.LOG_RESPONSE_CODE_PREFIX.concat("6")
        );

        return ResponseEntity
                .status(HttpStatus.valueOf(response.getHttpStatus()))
                .body(response);
    }
}
