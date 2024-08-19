package com.example.person_service.service;


import com.example.person_service.entity.ProfileHistory;
import com.example.person_service.repository.ProfileHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProfileHistoryService {


    private final ProfileHistoryRepository repository;

    public Mono<ProfileHistory> createHistory(ProfileHistory history) {

        return repository.save(history);
    }
}
