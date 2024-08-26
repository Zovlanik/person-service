package com.example.person_service.service;


import com.example.common.MerchantDto;
import com.example.person_service.entity.Merchant;
import com.example.person_service.mapper.MerchantMapper;
import com.example.person_service.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantMapper mapper;
    private final MerchantRepository repository;

    public Mono<Merchant> createMerchant(MerchantDto merchantDto) {
        return repository.save(mapper.map(merchantDto));
    }

    public Mono<Merchant> findById(UUID id) {
        return repository.findById(id);
    }

    public Mono<Merchant> updateMerchant(UUID id, MerchantDto merchantDto) {
        return repository.findById(id)
                .flatMap(merchant -> {
                    Merchant newMerchant = mapper.map(merchantDto);
                    newMerchant.setId(id);
                    return repository.save(newMerchant);
                });
    }

    public Mono<Void> deleteById(UUID id) {
        return repository.deleteById(id);
    }
}
