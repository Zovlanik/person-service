package com.example.person_service.controller;

import com.example.common.IndividualDto;
import com.example.person_service.entity.Individual;
import com.example.person_service.service.IndividualsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/individual")
public class IndividualsController {

    private final IndividualsService individualsService;

    @PostMapping
    public Mono<Individual> createIndividual (@RequestBody IndividualDto individualDto){
        return individualsService.createIndividual(individualDto);
    }

    @PutMapping
    public Mono<Individual> updateIndividual (@RequestBody IndividualDto individualDto){
        return individualsService.updateIndividual(individualDto);
    }

    @GetMapping("/{id}")
    public Mono<Individual> getIndividualById (@PathVariable String id){
        //todo: добавить валидацию на id, что это uuid
        return individualsService.findById(UUID.fromString(id));
    }

    @DeleteMapping("/{id}")
    public Mono<Individual> deleteIndividualById (@PathVariable String id){
        //todo: добавить валидацию на id, что это uuid
        return individualsService.deleteById(UUID.fromString(id));
    }

}
