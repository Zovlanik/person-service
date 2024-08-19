package com.example.person_service.mapper;


import com.example.common.CountryDto;
import com.example.person_service.entity.Country;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    @InheritInverseConfiguration
    Country map(CountryDto countryDto);

    CountryDto map (Country country);
}
