package com.example.person_service.controller;


import com.example.common.AddressDto;
import com.example.person_service.entity.Address;
import com.example.person_service.service.AddressService;
import com.example.person_service.utils.UUIDValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public Mono<Address> createAddress(AddressDto addressDto) {
        return addressService.createAddress(addressDto);
    }

    @GetMapping("/{id}")
    public Mono<Address> getAddress(@PathVariable String id) {
        if (UUIDValidator.isValidUUID(id)) {
            return addressService.getAddress(UUID.fromString(id));
        }
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/{id}")
    public Mono<Address> updateAddress(@PathVariable String id,@RequestBody AddressDto addressDto) {
        if (UUIDValidator.isValidUUID(id)) {
            return addressService.updateAddress(UUID.fromString(id),addressDto);
        }
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAddress(@PathVariable String id) {
        if (UUIDValidator.isValidUUID(id)) {
            return addressService.deleteAddress(UUID.fromString(id));
        }
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
}
