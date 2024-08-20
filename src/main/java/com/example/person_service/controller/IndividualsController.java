package com.example.person_service.controller;

import com.example.common.IndividualDto;
import com.example.person_service.entity.Individual;
import com.example.person_service.service.IndividualsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Mono<Individual> createIndividual (@RequestBody IndividualDto individualDto){
        return individualsService.createIndividual(individualDto);
    }

    @PostMapping("/update")
    public Mono<Individual> updateIndividual (@RequestBody IndividualDto individualDto){
        return individualsService.updateIndividual(individualDto);
    }


    @PostMapping("/getById")
    public Mono<Individual> getIndividualById (@RequestBody UUID id){
        return individualsService.findById(id);
    }

}
