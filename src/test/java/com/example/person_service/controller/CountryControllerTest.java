package com.example.person_service.controller;

import com.example.common.CountryDto;
import com.example.person_service.entity.Country;
import com.example.person_service.mapper.CountryMapper;
import com.example.person_service.repository.CountryRepository;
import com.example.person_service.testcontainersettings.PostgresTestContainerConfig;
import com.example.person_service.util.DataDBUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.person_service.util.DataTestUtil.getCountryDto;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Import(PostgresTestContainerConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CountryControllerTest {


    @Autowired
    WebTestClient webTestClient;

    @Autowired
    CountryRepository repository;

    @Autowired
    CountryMapper mapper;

    @Autowired
    DataDBUtil dataDBUtil;

    @Test
    void createCountry() {
        CountryDto countryDto = getCountryDto(LocalDateTime.now());
        webTestClient.post()
                .uri("/api/v1/country")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(countryDto), CountryDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo(countryDto.getName());
    }

    @Test
    void getCountry() {
        CountryDto countryDto = getCountryDto(LocalDateTime.now());
        Country country = repository.save(mapper.map(countryDto)).block();

        webTestClient.get()
                .uri("/api/v1/country/" + country.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo(countryDto.getName());
    }

    @Test
    void updateCountry() {
        CountryDto countryDto = getCountryDto(LocalDateTime.now());
        Country country = repository.save(mapper.map(countryDto)).block();
        CountryDto newCountryDto = getCountryDto(LocalDateTime.now());
        newCountryDto.setName("Absolutely New Name");

        webTestClient.put()
                .uri("/api/v1/country/" + country.getId())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(newCountryDto), CountryDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("Absolutely New Name");
    }

    @Test
    void deleteCountry() {
        CountryDto countryDto = getCountryDto(LocalDateTime.now());
        Country country = repository.save(mapper.map(countryDto)).block();

        Optional<Country> savedCountry = repository.findById(country.getId()).blockOptional();
        assertTrue(savedCountry.isPresent(), "Country should exist before deletion");

        webTestClient.delete()
                .uri("/api/v1/country/" + country.getId())
                .exchange()
                .expectStatus().isOk();

        Optional<Country> deletedCountry = repository.findById(country.getId()).blockOptional();
        assertFalse(deletedCountry.isPresent(), "Country should be deleted after the delete request");
    }
}