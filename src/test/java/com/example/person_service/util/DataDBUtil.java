package com.example.person_service.util;

import com.example.person_service.entity.Country;
import com.example.person_service.repository.AddressRepository;
import com.example.person_service.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataDBUtil {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CountryRepository countryRepository;


    public Country createCountry(){
        Country country = new Country();
        country.setCreated(LocalDateTime.now());
        country.setUpdated(LocalDateTime.now());
        country.setName("Country Name");
        country.setAlpha2("CN");
        country.setAlpha3("CNA");
        country.setStatus("ACTIVE");
        return countryRepository.save(country).block();
    }

}
