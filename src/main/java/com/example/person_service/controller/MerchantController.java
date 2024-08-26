package com.example.person_service.controller;

import com.example.common.MerchantDto;
import com.example.person_service.entity.Merchant;
import com.example.person_service.service.MerchantService;
import com.example.person_service.utils.UUIDValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping
    public Mono<Merchant> createMerchant (@RequestBody MerchantDto merchantDto){
        return merchantService.createMerchant(merchantDto);
    }

    @PutMapping("/{id}")
    public Mono<Merchant> updateMerchant(@PathVariable String id, @RequestBody MerchantDto merchantDto) {
        if (UUIDValidator.isValidUUID(id)) {
            return merchantService.updateMerchant(UUID.fromString(id), merchantDto);
        }
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{id}")
    public Mono<Merchant> getMerchantById(@PathVariable String id) {
        if (UUIDValidator.isValidUUID(id)) {
            return merchantService.findById(UUID.fromString(id));
        }
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteMerchantById(@PathVariable String id) {
        if (UUIDValidator.isValidUUID(id)) {
            return merchantService.deleteById(UUID.fromString(id));
        }
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
}
