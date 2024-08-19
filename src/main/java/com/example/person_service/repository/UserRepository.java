package com.example.person_service.repository;


import com.example.person_service.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface UserRepository extends R2dbcRepository<User, UUID>{

}
