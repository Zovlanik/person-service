package com.example.person_service.service;


import com.example.common.IndividualDto;
import com.example.person_service.entity.Individual;
import com.example.person_service.entity.ProfileHistory;
import com.example.person_service.mapper.IndividualsMapper;
import com.example.person_service.mapper.profilehistorymapper.IndividualHistoryMapper;
import com.example.person_service.repository.IndividualRepository;
import com.example.person_service.utils.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import lombok.RequiredArgsConstructor;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IndividualsService {

    private final IndividualsMapper mapper;
    private final IndividualRepository repository;
    private final ProfileHistoryService historyService;
    private final IndividualHistoryMapper historyMapper = new IndividualHistoryMapper();

    public Mono<Individual> createIndividual(IndividualDto individualDto){

        Individual individual = mapper.map(individualDto);

        ProfileHistory history = ProfileHistory.builder()
                .created(LocalDateTime.now())
                .profileId(individualDto.getUserId())
                .changedValues(JsonConverter.convertObjectToJson(individual))
                .build();

        return repository.save(individual)
                .flatMap(savedIndividual -> historyService.createHistory(history)
                        .thenReturn(savedIndividual));
    }

    public Mono<Individual> updateIndividual(IndividualDto individualDto){
        // получить индивидуала по айдишнику
        // сравнить текущую дто и новую
        // измененные поля пишу сюда в историю
        // использовать JsonNode!

        return repository.findByUserId(individualDto.getUserId())
                .flatMap(individualFromRepo -> {
                    Individual newIndividual = mapper.map(individualDto);
                    newIndividual.setId(individualFromRepo.getId());
                    return repository.save(newIndividual)
                            .flatMap(savedIndividual -> {
                                // Сравниваем через Джаверс
                                Javers javers = JaversBuilder.javers().build();
                                Diff diff = javers.compare(individualFromRepo, savedIndividual);

                                // Создаем объект с измененными значениями
                                Individual individualHistory = historyMapper.map(diff);

                                // Создаем запись истории профиля
                                ProfileHistory history = ProfileHistory.builder()
                                        .created(LocalDateTime.now())
                                        .profileId(individualDto.getUserId())
                                        .changedValues(JsonConverter.convertObjectToJson(individualHistory))
                                        .build();

                                // Сохраняем историю и возвращаем сохраненного пользователя
                                return historyService.createHistory(history)
                                        .thenReturn(savedIndividual);
                            });
                });

    }

    public Mono<Individual> findById(UUID id){
        return repository.findById(id);
    }

































    public Mono<Individual> test(IndividualDto individualDto){

        Mono<Individual> individualFromRepo = repository.findById(individualDto.getUserId());

        Javers javers = JaversBuilder.javers().build();
        Diff diff = javers.compare(individualFromRepo,individualDto);







        String diffJson = javers.getJsonConverter().toJson(diff);

        // Используем ObjectMapper из Jackson для десериализации JSON-строки в JsonNode
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(diffJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        JsonNode node = JsonNodeFactory.instance.objectNode();

//        JsonNode jsonNode;// создаю из значений diff
        // конвертирую в строку
        // строку сохраняю в БД



        Individual mappedIndividual = historyMapper.map(diff);
        ProfileHistory history = ProfileHistory.builder()
                .created(LocalDateTime.now())
                .profileId(individualDto.getUserId())
                .changedValues(JsonConverter.convertObjectToJson(mappedIndividual))
                .build();

        ValueChange change = diff.getChangesByType(ValueChange.class).get(0);

        //todo: уточнить у Жени как правильно вообще вызывать Mono<void> и встраивать в последовательность выполнения.


        return repository.save(mapper.map(individualDto))
                .flatMap(savedIndividual -> historyService.createHistory(history)
                        .thenReturn(savedIndividual));
    }

}
