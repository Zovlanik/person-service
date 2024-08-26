package com.example.person_service.controller;


import com.example.common.AddressDto;
import com.example.person_service.entity.Address;
import com.example.person_service.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public Mono<Address> createAddress (AddressDto addressDto){
        return addressService.createAddress(addressDto);
    }
}
