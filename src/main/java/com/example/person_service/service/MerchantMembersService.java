package com.example.person_service.service;


import com.example.common.MerchantMembersDto;
import com.example.person_service.entity.MerchantMembers;
import com.example.person_service.mapper.MerchantMembersMapper;
import com.example.person_service.repository.MerchantMembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MerchantMembersService {

    private final MerchantMembersMapper mapper;
    private final MerchantMembersRepository repository;

    public Mono<MerchantMembers> createMerchantMembers(MerchantMembersDto merchantMembersDto){
        return repository.save(mapper.map(merchantMembersDto));
    }

}
