package com.prevalentware.prueba_tecnica.infrastructure.input.rest;

import com.prevalentware.prueba_tecnica.application.dto.response.CountryResponseDto;
import com.prevalentware.prueba_tecnica.application.service.ICountryService;
import com.prevalentware.prueba_tecnica.infrastructure.utils.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
@RequiredArgsConstructor
public class CountryRestController {

    private final ICountryService countryHandler;

    @GetMapping("/")
    public ResponseEntity<APIResponse<List<CountryResponseDto>>> getAllCountries
}
