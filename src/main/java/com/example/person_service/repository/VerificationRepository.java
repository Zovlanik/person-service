package com.example.person_service.repository;


import com.example.person_service.entity.Verification;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface VerificationRepository extends R2dbcRepository<Verification, UUID>{

}
