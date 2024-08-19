package com.example.person_service.repository;


import com.example.person_service.entity.MerchantMembers;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface MerchantMembersRepository extends R2dbcRepository<MerchantMembers, UUID>{

}
