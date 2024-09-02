package com.example.person_service.util;

import com.example.person_service.entity.Address;
import com.example.person_service.entity.Country;
import com.example.person_service.entity.User;
import com.example.person_service.repository.AddressRepository;
import com.example.person_service.repository.CountryRepository;
import com.example.person_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataDBUtil {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    UserRepository userRepository;


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

    public Address createAddress_withCountry(){
        Address address = new Address();
        address.setCreated(LocalDateTime.now());
        address.setUpdated(LocalDateTime.now());
        address.setArchived(LocalDateTime.now());
        address.setCountryId(createCountry().getId());
        address.setAddress("Some String Address");
        address.setZipCode("123456");
        address.setCity("Some City");
        address.setState("Some state");
        return addressRepository.save(address).block();
    }

    public User createUser_withAddress_withCountry(){
        User user = new User();
        user.setSecretKey("123");
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        user.setFirstName("FirstNameUser");
        user.setLastName("LastNameUser");
        user.setVerifiedAt(LocalDateTime.now());
        user.setArchivedAt(LocalDateTime.now());
        user.setStatus("ACTIVE");
        user.setFilled(true);
        user.setAddressId(createAddress_withCountry().getId());

        return userRepository.save(user).block();
    }

}
