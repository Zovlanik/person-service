package com.example.person_service.repository;


import com.example.person_service.entity.Country;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CountryRepository extends R2dbcRepository<Country, Integer>{

}
