package com.example.person_service.repository;


import com.example.person_service.entity.Individual;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IndividualRepository extends R2dbcRepository<Individual, UUID> {

    Mono<Individual> findByUserId (UUID userId);
}
