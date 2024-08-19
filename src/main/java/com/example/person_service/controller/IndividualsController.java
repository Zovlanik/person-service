package com.example.person_service.controller;

import com.example.common.IndividualDto;
import com.example.person_service.entity.Individual;
import com.example.person_service.service.IndividualsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/person-service/individual")
public class IndividualsController {

    private final IndividualsService individualsService;

    @PostMapping("/create")
    public Mono<Individual> createIndividual (IndividualDto individualDto){
        return individualsService.createIndividual(individualDto);
    }

    @PostMapping("/update")
    public Mono<Individual> updateIndividual (IndividualDto individualDto){
        return individualsService.updateIndividual(individualDto);
    }


    @PostMapping("/getById")
    public Mono<Individual> getIndividualById (UUID id){
        return individualsService.findById(id);
    }

}
