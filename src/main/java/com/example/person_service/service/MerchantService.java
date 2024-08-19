package com.example.person_service.service;


import com.example.common.MerchantDto;
import com.example.person_service.entity.Merchant;
import com.example.person_service.mapper.MerchantMapper;
import com.example.person_service.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantMapper mapper;
    private final MerchantRepository repository;

    public Mono<Merchant> createMerchant(MerchantDto merchantDto){
        return repository.save(mapper.map(merchantDto));
    }

}
