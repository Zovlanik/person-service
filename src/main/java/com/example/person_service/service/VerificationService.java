package com.example.person_service.service;


import com.example.common.VerificatonDto;
import com.example.person_service.entity.Verification;
import com.example.person_service.mapper.VerificationMapper;
import com.example.person_service.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class VerificationService {

    private final VerificationMapper mapper;
    private final VerificationRepository repository;

    public Mono<Verification> verify(VerificatonDto verificatonDto){
        return repository.save(mapper.map(verificatonDto));
    }

}
