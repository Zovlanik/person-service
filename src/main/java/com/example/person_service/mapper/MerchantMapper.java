package com.example.person_service.mapper;


import com.example.common.MerchantDto;
import com.example.person_service.entity.Merchant;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MerchantMapper {

    @InheritInverseConfiguration
    Merchant map(MerchantDto merchantDto);

    MerchantDto map (Merchant merchant);
}
