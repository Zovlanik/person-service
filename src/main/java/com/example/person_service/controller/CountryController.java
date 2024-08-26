package com.example.person_service.controller;


import com.example.common.CountryDto;
import com.example.person_service.entity.Country;
import com.example.person_service.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/country")
public class CountryController {

    private final CountryService countryService;

    @PostMapping
    public Mono<Country> createCountry(@RequestBody CountryDto countryDto) {
        return countryService.createCountry(countryDto);
    }

    @GetMapping("/{id}")
    public Mono<Country> getCountry(@PathVariable Integer id) {
        return countryService.getCountry(id);
    }

    @PutMapping("/{id}")
    public Mono<Country> updateCountry(@PathVariable Integer id,@RequestBody CountryDto countryDto) {
        return countryService.updateCountry(id, countryDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCountry(@PathVariable Integer id) {
        return countryService.deleteCountry(id);
    }
}
