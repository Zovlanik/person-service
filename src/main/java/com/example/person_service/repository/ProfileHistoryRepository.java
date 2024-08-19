package com.example.person_service.repository;


import com.example.person_service.entity.ProfileHistory;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface ProfileHistoryRepository extends R2dbcRepository<ProfileHistory, UUID>{

}
