package com.example.person_service.service;

import com.example.common.IndividualDto;
import com.example.person_service.entity.Individual;
import com.example.person_service.entity.ProfileHistory;
import com.example.person_service.mapper.IndividualsMapper;
import com.example.person_service.mapper.IndividualsMapperImpl;
import com.example.person_service.repository.IndividualRepository;
import com.example.person_service.utils.JsonConverter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.person_service.util.DataTestUtil.*;

class IndividualsServiceTest {

    IndividualsMapper mapper = new IndividualsMapperImpl();
    IndividualRepository repository = Mockito.mock(IndividualRepository.class);
    ProfileHistoryService historyService = Mockito.mock(ProfileHistoryService.class);
    IndividualsService service = new IndividualsService(mapper, repository, historyService);

    @Test
    void createIndividual() {
        LocalDateTime now = LocalDateTime.now();

        IndividualDto newIndividualDto = getIndividualDto(now);

        Individual individualFromRepo = getIndividual(now);

        ProfileHistory profileHistorySaved = ProfileHistory.builder()
                .created(now)
                .profileId(UUID.fromString("bb9451be-65c3-49d9-951d-5e6cab79c57f"))
                .changedValues(JsonConverter.convertObjectToJson(individualFromRepo))
                .build();

        Mockito.when(repository.save(Mockito.any(Individual.class))).thenReturn(Mono.just(individualFromRepo));
        Mockito.when(historyService.createHistory(Mockito.any(ProfileHistory.class))).thenReturn(Mono.just(profileHistorySaved));

        StepVerifier.create(service.createIndividual(newIndividualDto))
                .expectNext(individualFromRepo)
                .verifyComplete();
    }

    @Test
    void updateIndividual() {
        LocalDateTime now = LocalDateTime.now();

        IndividualDto newIndividualDto = getIndividualDto(now);

        Individual individualFromRepo = getIndividual(now);

        Individual changedIndividual = getChangedIndividual(now);

        ProfileHistory profileHistorySaved = ProfileHistory.builder()
                .created(now)
                .profileId(UUID.fromString("bb9451be-65c3-49d9-951d-5e6cab79c57f"))
                .changedValues(JsonConverter.convertObjectToJson(changedIndividual))
                .build();

        Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Mono.just(individualFromRepo));
        Mockito.when(repository.save(Mockito.any(Individual.class))).thenReturn(Mono.just(changedIndividual));
        Mockito.when(historyService.createHistory(Mockito.any(ProfileHistory.class))).thenReturn(Mono.just(profileHistorySaved));

        StepVerifier.create(service.updateIndividual(individualFromRepo.getId(),newIndividualDto))
                .expectNext(changedIndividual)
                .verifyComplete();
    }

    @Test
    void findById() {
        Individual individualFromRepo = getIndividual(LocalDateTime.now());
        Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Mono.just(individualFromRepo));

        Mono<Individual> byId = service.findById(individualFromRepo.getId());
        StepVerifier.create(byId)
                .assertNext(individual -> {
                    individual.getUserId().equals(individualFromRepo.getUserId());
                })
                .verifyComplete();
    }


    @Test
    void deleteById() {
        Individual individualFromRepo = getIndividual(LocalDateTime.now());
        Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Mono.just(individualFromRepo));
        Mockito.when(repository.deleteById(Mockito.any(UUID.class))).thenReturn(Mono.empty());

        Mono<Individual> individualMono = service.deleteById(individualFromRepo.getId());

        StepVerifier.create(individualMono)
                .expectNext(individualFromRepo)
                .verifyComplete();
    }


}