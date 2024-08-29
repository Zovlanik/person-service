package com.example.person_service.controller;

import com.example.common.AddressDto;
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
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static com.example.person_service.util.DataTestUtil.getAddressDto;

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
                .body(Mono.just(addressDto), AddressDto.class)
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