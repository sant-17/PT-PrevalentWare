package com.prevalentware.prueba_tecnica.infrastructure.input.rest;

import com.prevalentware.prueba_tecnica.application.dto.response.UserResponseDto;
import com.prevalentware.prueba_tecnica.application.service.IUserService;
import com.prevalentware.prueba_tecnica.infrastructure.utils.APIResponse;
import com.prevalentware.prueba_tecnica.infrastructure.utils.Constant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User", description = "La API de la tabla User")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserService userService;

    @Operation(
            summary = "Obtener todos los usuarios.",
            description = "Este endpoint devuelve una lista de usuarios. " +
                    "Los parámetros 'size' y 'number' permiten paginar los resultados. " +
                    "'Size' especifica el número de países a devolver y 'number' especifica el número de página.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se encontraron los registros de los usuarios."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Los parámetros 'size' y/o 'number' son de tipos incorrectos o contienen valores no permitidos.",
                    content = @Content(schema = @Schema(implementation = APIResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontraron los registros de los usuarios.",
                    content = @Content(schema = @Schema(implementation = APIResponse.class))),
    })
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

    @Operation(
            summary = "Obtener información del usuario por correo electrónico.",
            description = "Recupera los detalles del usuario asociado al email proporcionado. "
                    + "Devuelve un objeto APIResponse con la información del usuario o un mensaje de error si no se encuentra."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se encontró el registro del usuario."),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró a un usuario asociado al email proporcionado.",
                    content = @Content(schema = @Schema(implementation = APIResponse.class))),
    })
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
