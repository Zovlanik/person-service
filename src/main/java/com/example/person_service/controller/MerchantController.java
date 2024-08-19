package com.example.person_service.controller;

import com.example.common.MerchantDto;
import com.example.person_service.entity.Merchant;
import com.example.person_service.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/person-service/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping("")
    public Mono<Merchant> createMerchant (MerchantDto merchantDto){
        return merchantService.createMerchant(merchantDto);
    }
}
