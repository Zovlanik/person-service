package com.example.person_service.controller;

import com.example.common.AddressDto;
import com.example.person_service.entity.Address;
import com.example.person_service.mapper.AddressMapper;
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
import java.util.Optional;

import static com.example.person_service.util.DataTestUtil.getAddressDto;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    AddressMapper mapper;

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
        AddressDto addressDto = getAddressDto(LocalDateTime.now());
        Address address = repository.save(mapper.map(addressDto)).block();

        webTestClient.get()
                .uri("/api/v1/address/" + address.getId().toString())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(address.getId().toString())
                .jsonPath("$.countryId").isEqualTo(addressDto.getCountryId())
                .jsonPath("$.address").isEqualTo(addressDto.getAddress());
    }

    @Test
    void updateAddress() {
        AddressDto addressDto = getAddressDto(LocalDateTime.now());
        Address address = repository.save(mapper.map(addressDto)).block();
        AddressDto newAddressDto = getAddressDto(LocalDateTime.now());
        newAddressDto.setAddress("Absolutely new Address");

        webTestClient.put()
                .uri("/api/v1/address/" + address.getId().toString())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(newAddressDto), AddressDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(address.getId().toString())
                .jsonPath("$.countryId").isEqualTo(addressDto.getCountryId())
                .jsonPath("$.address").isEqualTo("Absolutely new Address");
    }

    @Test
    void deleteAddress() {
        AddressDto addressDto = getAddressDto(LocalDateTime.now());
        Address address = repository.save(mapper.map(addressDto)).block();

        Optional<Address> savedAddress = repository.findById(address.getId()).blockOptional();
        assertTrue(savedAddress.isPresent(), "Address should exist before deletion");

        webTestClient.delete()
                .uri("/api/v1/address/" + address.getId().toString())
                .exchange()
                .expectStatus().isOk();

        Optional<Address> deletedAddress = repository.findById(address.getId()).blockOptional();
        assertFalse(deletedAddress.isPresent(), "Address should be deleted after the delete request");

    }
}