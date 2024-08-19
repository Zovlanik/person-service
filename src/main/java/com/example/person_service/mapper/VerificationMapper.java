package com.example.person_service.mapper;


import com.example.common.VerificatonDto;
import com.example.person_service.entity.Verification;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VerificationMapper {

    @InheritInverseConfiguration
    Verification map(VerificatonDto verificatonDto);

    VerificatonDto map (Verification verification);
}
