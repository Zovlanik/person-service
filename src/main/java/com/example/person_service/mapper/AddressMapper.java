package com.example.person_service.mapper;


import com.example.common.AddressDto;
import com.example.person_service.entity.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @InheritInverseConfiguration
    Address map(AddressDto addressDto);

    AddressDto map (Address address);
}
