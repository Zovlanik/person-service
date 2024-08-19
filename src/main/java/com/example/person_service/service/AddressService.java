package com.example.person_service.service;


import com.example.common.AddressDto;
import com.example.person_service.entity.Address;
import com.example.person_service.mapper.AddressMapper;
import com.example.person_service.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressMapper mapper;
    private final AddressRepository repository;

    public Mono<Address> createAddress(AddressDto addressDto){
        return repository.save(mapper.map(addressDto));
    }

}
