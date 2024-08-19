package com.example.person_service.service;


import com.example.person_service.entity.MerchantMemberInvitation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class MerchantMemberInvitationService {


    public Mono<MerchantMemberInvitation> sendInvitation(MerchantMemberInvitation merchantMemberInvitation){
        //todo: пока не понимаю как вызывать этот слой, что тут делать?
        // todo: возвращать здесь надо DTO приглашения? Как оно должно выглядеть? Что в нём должно содержаться?
        return Mono.just(merchantMemberInvitation);
    }

}
