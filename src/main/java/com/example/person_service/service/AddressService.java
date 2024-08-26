package com.example.person_service.service;


import com.example.common.AddressDto;
import com.example.common.CountryDto;
import com.example.person_service.entity.Address;
import com.example.person_service.entity.Country;
import com.example.person_service.mapper.AddressMapper;
import com.example.person_service.mapper.CountryMapper;
import com.example.person_service.repository.AddressRepository;
import com.example.person_service.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressMapper mapper;
    private final AddressRepository repository;

    public Mono<Address> createAddress(AddressDto addressDto){
        return repository.save(mapper.map(addressDto));
    }

    public Mono<Address> getAddress(UUID id){
        return repository.findById(id);
    }

    public Mono<Address> updateAddress(UUID id, AddressDto addressDto){
        return repository.findById(id)
                .flatMap(address -> {
                    Address newAddress = mapper.map(addressDto);
                    newAddress.setId(id);
                    return repository.save(newAddress);
                });
    }

    public Mono<Void> deleteAddress(UUID id){
        return repository.deleteById(id);
    }
}
