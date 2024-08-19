package com.example.person_service.mapper;


import com.example.common.MerchantMembersDto;
import com.example.person_service.entity.MerchantMembers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MerchantMembersMapper {

    @InheritInverseConfiguration
    MerchantMembers map(MerchantMembersDto merchantMembersDto);

    MerchantMembersDto map (MerchantMembers merchantMembers);
}
