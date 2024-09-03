package com.example.person_service.service;

import com.example.common.CountryDto;
import com.example.person_service.entity.Country;
import com.example.person_service.mapper.CountryMapperImpl;
import com.example.person_service.repository.CountryRepository;
import com.example.person_service.util.DataTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.person_service.util.DataTestUtil.getCountryDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    @Spy
    CountryMapperImpl mapper;
    @Mock
    CountryRepository repository;
    @InjectMocks
    CountryService service;

    @Test
    void createCountry() {

        CountryDto countryDto = getCountryDto(LocalDateTime.now());
        Mockito.when(mapper.map(any(CountryDto.class))).thenCallRealMethod();
        Country country = DataTestUtil.getCountry(LocalDateTime.now());
        Mockito.when(repository.save(any(Country.class))).thenReturn(Mono.just(country));


        Mono<Country> countryFromService = service.createCountry(countryDto);

        StepVerifier.create(countryFromService)
                .assertNext( c -> {
                    c.getAlpha2().equals(country.getAlpha2());
                })
                .verifyComplete();
    }

    @Test
    void getCountry() {
        Country country = DataTestUtil.getCountry(LocalDateTime.now());
        Mockito.when(repository.findById(anyInt())).thenReturn(Mono.just(country));

        Mono<Country> countryFromService = service.getCountry(1);

        StepVerifier.create(countryFromService)
                .assertNext( c -> {
                    c.getAlpha2().equals(country.getAlpha2());
                })
                .verifyComplete();
    }

    @Test
    void updateCountry() {
        CountryDto countryDto = getCountryDto(LocalDateTime.now());
        countryDto.setName("Some Country AfterSave");
        Mockito.when(mapper.map(any(CountryDto.class))).thenCallRealMethod();
        Country country = DataTestUtil.getCountry(LocalDateTime.now());
        Country countryAfterSave = DataTestUtil.getCountry(LocalDateTime.now());
        countryAfterSave.setName("Some Country AfterSave");
        Mockito.when(repository.findById(anyInt())).thenReturn(Mono.just(country));
        Mockito.when(repository.save(any(Country.class))).thenReturn(Mono.just(countryAfterSave));


        Mono<Country> countryFromService = service.updateCountry(1,countryDto);

        StepVerifier.create(countryFromService)
                .assertNext( c -> {
                    c.getName().equals(country.getName());
                })
                .verifyComplete();
    }

    @Test
    void deleteCountry() {
        Country country = DataTestUtil.getCountry(LocalDateTime.now());
        Mockito.when(repository.deleteById(anyInt())).thenReturn(Mono.empty());

        Mono<Void> voidMono = service.deleteCountry(country.getId());

        StepVerifier.create(voidMono)
                .verifyComplete();
    }
}