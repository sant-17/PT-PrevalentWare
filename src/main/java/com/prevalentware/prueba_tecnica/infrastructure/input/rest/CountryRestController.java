package com.prevalentware.prueba_tecnica.infrastructure.input.rest;

import com.prevalentware.prueba_tecnica.application.dto.response.CountryResponseDto;
import com.prevalentware.prueba_tecnica.application.service.ICountryService;
import com.prevalentware.prueba_tecnica.infrastructure.utils.APIResponse;
import com.prevalentware.prueba_tecnica.infrastructure.utils.Constant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Country", description = "La API de la tabla Country")
@RestController
@RequestMapping("/api/v1/country")
@RequiredArgsConstructor
public class CountryRestController {

    private final ICountryService countryService;

    @Operation(
            summary = "Obtener todos los países.",
            description = "Este endpoint devuelve una lista de países. " +
                    "Los parámetros 'size' y 'number' permiten paginar los resultados. " +
                    "'Size' especifica el número de países a devolver y 'number' especifica el número de página.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se encontraron los registros de los países."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Los parámetros 'size' y/o 'number' son de tipos incorrectos o contienen valores no permitidos.",
                    content = @Content(schema = @Schema(implementation = APIResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontraron los registros de los países.",
                    content = @Content(schema = @Schema(implementation = APIResponse.class))),
    })
    @GetMapping("all/size/{size}/number/{number}")
    public ResponseEntity<APIResponse<List<CountryResponseDto>>> getAllCountries(@PathVariable @Min(1) Integer size, @PathVariable @Min(0) Integer number){
        List<CountryResponseDto> countryResponseList = countryService.getAllCountries(number, size);

        APIResponse<List<CountryResponseDto>> response = APIResponse.ok(
                countryResponseList,
                Constant.getLogResponseHashMap(),
                Constant.LOG_RESPONSE_CODE_PREFIX.concat("6")
        );

        return ResponseEntity
                .status(HttpStatus.valueOf(response.getHttpStatus()))
                .body(response);
    }
}
