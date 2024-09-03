package com.example.person_service.controller;

import com.example.common.AddressDto;
import com.example.common.CountryDto;
import com.example.common.IndividualDto;
import com.example.common.UserDto;
import com.example.person_service.entity.Individual;
import com.example.person_service.entity.User;
import com.example.person_service.mapper.IndividualsMapper;
import com.example.person_service.repository.IndividualRepository;
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

import static com.example.person_service.util.DataTestUtil.getIndividualDto_withEmptyUserId;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Import(PostgresTestContainerConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class IndividualsControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    IndividualRepository individualsRepository;

    @Autowired
    IndividualsMapper mapper;

    @Autowired
    DataDBUtil dataDBUtil;

    @Test
    void createIndividual() {
        User user_withAddress_withCountry = dataDBUtil.createUser_withAddress_withCountry();
        IndividualDto individualDto = getIndividualDto_withEmptyUserId(LocalDateTime.now());
        individualDto.setUserId(user_withAddress_withCountry.getId());

        webTestClient.post()
                .uri("/api/v1/individual")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(individualDto), IndividualDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty();
    }

    @Test
    void getIndividualById() {
        User user_withAddress_withCountry = dataDBUtil.createUser_withAddress_withCountry();
        IndividualDto individualDto = getIndividualDto_withEmptyUserId(LocalDateTime.now());
        individualDto.setUserId(user_withAddress_withCountry.getId());
        Individual individual = individualsRepository.save(mapper.map(individualDto)).block();

        webTestClient.get()
                .uri("/api/v1/individual/" + individual.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.passportNumber").isEqualTo(individualDto.getPassportNumber());
    }

    @Test
    void updateIndividual() {
        User user_withAddress_withCountry = dataDBUtil.createUser_withAddress_withCountry();
        IndividualDto individualDto = getIndividualDto_withEmptyUserId(LocalDateTime.now());
        individualDto.setUserId(user_withAddress_withCountry.getId());
        Individual individual = individualsRepository.save(mapper.map(individualDto)).block();

        IndividualDto newIndividualDto = getIndividualDto_withEmptyUserId(LocalDateTime.now());
        newIndividualDto.setUserId(user_withAddress_withCountry.getId());
        newIndividualDto.setPassportNumber("98 76 54321");

        webTestClient.put()
                .uri("/api/v1/individual/" + individual.getId())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(newIndividualDto), IndividualDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.passportNumber").isEqualTo(newIndividualDto.getPassportNumber());
    }

    @Test
    void deleteIndividualById() {

        User user_withAddress_withCountry = dataDBUtil.createUser_withAddress_withCountry();
        IndividualDto individualDto = getIndividualDto_withEmptyUserId(LocalDateTime.now());
        individualDto.setUserId(user_withAddress_withCountry.getId());
        Individual individual = individualsRepository.save(mapper.map(individualDto)).block();

        Optional<Individual> savedIndividual = individualsRepository.findById(individual.getId()).blockOptional();
        assertTrue(savedIndividual.isPresent(), "Individual should exist before deletion");

        webTestClient.delete()
                .uri("/api/v1/individual/" + individual.getId())
                .exchange()
                .expectStatus().isOk();

        Optional<Individual> deletedIndividual = individualsRepository.findById(individual.getId()).blockOptional();
        assertFalse(deletedIndividual.isPresent(), "Individual should be deleted after the delete request");

    }
}