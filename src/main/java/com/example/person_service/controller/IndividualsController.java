package com.example.person_service.controller;

import com.example.common.IndividualDto;
import com.example.person_service.entity.Individual;
import com.example.person_service.service.IndividualsService;
import com.example.person_service.utils.UUIDValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/individual")
public class IndividualsController {

    private final IndividualsService individualsService;

    @PostMapping
    public Mono<Individual> createIndividual(@RequestBody IndividualDto individualDto) {
        return individualsService.createIndividual(individualDto);
    }

    @PutMapping("/{id}")
    public Mono<Individual> updateIndividual(@PathVariable String id, @RequestBody IndividualDto individualDto) {
        if (UUIDValidator.isValidUUID(id)) {
            return individualsService.updateIndividual(UUID.fromString(id), individualDto);
        }
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{id}")
    public Mono<Individual> getIndividualById(@PathVariable String id) {
        if (UUIDValidator.isValidUUID(id)) {
            return individualsService.findById(UUID.fromString(id));
        }
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public Mono<Individual> deleteIndividualById(@PathVariable String id) {
        if (UUIDValidator.isValidUUID(id)) {
            return individualsService.deleteById(UUID.fromString(id));
        }
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

}
