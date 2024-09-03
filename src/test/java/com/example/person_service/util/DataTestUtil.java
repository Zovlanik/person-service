package com.example.person_service.util;

import com.example.common.AddressDto;
import com.example.common.CountryDto;
import com.example.common.IndividualDto;
import com.example.person_service.entity.Address;
import com.example.person_service.entity.Country;
import com.example.person_service.entity.Individual;


import java.time.LocalDateTime;
import java.util.UUID;

public class DataTestUtil {

    public static IndividualDto getIndividualDto(LocalDateTime now) {
        IndividualDto newIndividualDto = new IndividualDto();
        newIndividualDto.setUserId(UUID.fromString("bb9451be-65c3-49d9-951d-5e6cab79c57f"));
        newIndividualDto.setPhoneNumber("+7(999)456-78-90");
        newIndividualDto.setEmail("new email");
        newIndividualDto.setCreated(now);
        newIndividualDto.setUpdated(now);
        newIndividualDto.setVerifiedAt(now);
        newIndividualDto.setArchivedAt(now);
        return newIndividualDto;
    }

    public static Individual getIndividual(LocalDateTime now) {
        Individual individualFromRepo = new Individual();
        individualFromRepo.setId(UUID.fromString("aa9451be-65c3-49d9-951d-5e6cab79c57f"));
        individualFromRepo.setUserId(UUID.fromString("bb9451be-65c3-49d9-951d-5e6cab79c57f"));
        individualFromRepo.setPhoneNumber("+7(123)456-78-90");
        individualFromRepo.setEmail("old email");
        individualFromRepo.setCreated(now);
        individualFromRepo.setUpdated(now);
        individualFromRepo.setVerifiedAt(now);
        individualFromRepo.setArchivedAt(now);
        individualFromRepo.setStatus("Some status");
        return individualFromRepo;
    }

    public static Individual getChangedIndividual(LocalDateTime now) {
        Individual changedIndividual = new Individual(); // should be equal to individualDTO
        changedIndividual.setId(UUID.fromString("aa9451be-65c3-49d9-951d-5e6cab79c57f"));
        changedIndividual.setUserId(UUID.fromString("bb9451be-65c3-49d9-951d-5e6cab79c57f"));
        changedIndividual.setPhoneNumber("+7(999)456-78-90");
        changedIndividual.setEmail("new email");
        changedIndividual.setCreated(now);
        changedIndividual.setUpdated(now);
        changedIndividual.setVerifiedAt(now);
        changedIndividual.setArchivedAt(now);
        changedIndividual.setStatus("Some status");
        return changedIndividual;
    }

    public static AddressDto getAddressDto(LocalDateTime now){
        AddressDto addressDto = new AddressDto();
        addressDto.setCreated(now);
        addressDto.setUpdated(now);
        addressDto.setArchived(now);
        addressDto.setCountryId(1);
        addressDto.setAddress("Some DTO Address");
        addressDto.setZipCode("123456");
        addressDto.setCity("Some DTO City");
        addressDto.setState("Some state");
        return addressDto;
    }

    public static CountryDto getCountryDto(LocalDateTime now){
        CountryDto countryDto = new CountryDto();
        countryDto.setCreated(now);
        countryDto.setName("CountryName");
        countryDto.setAlpha2("CN");
        countryDto.setAlpha3("CNN");
        countryDto.setStatus("ACTIVE");

        return countryDto;
    }

    public static IndividualDto getIndividualDto_withEmptyUserId(LocalDateTime now) {
        IndividualDto newIndividualDto = new IndividualDto();
        newIndividualDto.setUserId(null);
        newIndividualDto.setPhoneNumber("+7(999)456-78-90");
        newIndividualDto.setEmail("new email");
        newIndividualDto.setCreated(now);
        newIndividualDto.setUpdated(now);
        newIndividualDto.setVerifiedAt(now);
        newIndividualDto.setArchivedAt(now);
        newIndividualDto.setPassportNumber("12 34 56789");
        return newIndividualDto;
    }

    public static Address getAddress(LocalDateTime now){
        Address address = new Address();
        address.setId(UUID.fromString("bb9451be-65c3-49d9-951d-5e6cab79c57f"));
        address.setCreated(now);
        address.setUpdated(now);
        address.setArchived(now);
        address.setCountryId(1);
        address.setAddress("Some String Address");
        address.setZipCode("123456");
        address.setCity("Some City");
        address.setState("Some state");
        return address;
    }
    public static Country getCountry(LocalDateTime now){
        Country country = new Country();
        country.setId(1);
        country.setCreated(now);
        country.setUpdated(now);
        country.setName("Country Name");
        country.setAlpha2("CN");
        country.setAlpha3("CNN");
        country.setStatus("ACTIVE");
        return country;
    }
}