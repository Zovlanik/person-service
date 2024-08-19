package com.example.person_service.controller;


import com.example.common.CountryDto;
import com.example.person_service.entity.Country;
import com.example.person_service.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/person-service/country")
public class CountryController {

    private final CountryService countryService;

    @PostMapping("")
    public Mono<Country> createCountry (CountryDto countryDto){
        return countryService.createCountry(countryDto);
    }
}
