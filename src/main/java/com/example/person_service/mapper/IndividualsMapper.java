package com.example.person_service.mapper;


import com.example.common.IndividualDto;
import com.example.common.UserDto;
import com.example.person_service.entity.Individual;
import com.example.person_service.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IndividualsMapper {

    @InheritInverseConfiguration
    Individual map(IndividualDto individualDto);

    IndividualDto map (Individual individual);
}
