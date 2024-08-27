package com.example.person_service.controller;

import com.example.common.AddressDto;
import com.example.person_service.entity.Country;
import com.example.person_service.repository.AddressRepository;
import com.example.person_service.repository.CountryRepository;
import com.example.person_service.testcontainersettings.PostgresTestContainerConfig;
import com.example.person_service.util.DataDBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.person_service.util.DataTestUtil.getAddressDto;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Import(PostgresTestContainerConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class AddressControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    AddressRepository repository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    DataDBUtil dataDBUtil;

    @BeforeEach
    public void setUp(){
//        repository.deleteAll().block();
        dataDBUtil.createCountry();
    }


    @Test
    void createAddress() {
        AddressDto addressDto = getAddressDto(LocalDateTime.now());
        webTestClient.post()
                .uri("/api/v1/address")
                .accept(MediaType.APPLICATION_JSON)
                .body(addressDto, AddressDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.countryId").isEqualTo(addressDto.getCountryId());
    }

    @Test
    void getAddress() {
    }

    @Test
    void updateAddress() {
    }

    @Test
    void deleteAddress() {
    }
}