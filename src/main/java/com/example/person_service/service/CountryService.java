package com.example.person_service.service;


import com.example.common.CountryDto;
import com.example.person_service.entity.Country;
import com.example.person_service.mapper.CountryMapper;
import com.example.person_service.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryMapper mapper;
    private final CountryRepository repository;

    public Mono<Country> createCountry(CountryDto countryDto){
        return repository.save(mapper.map(countryDto));
    }

    public Mono<Country> getCountry(Integer id){
        return repository.findById(id);
    }

    public Mono<Country> updateCountry(CountryDto countryDto){ // todo: правильно ли в DTO добавлять айдишник, чтобы иметь возможность в будущем поиска по айдишнику?
        return repository.findByName(countryDto.getName())
                .flatMap(country -> {
                    Country newCountry = mapper.map(countryDto);
                    newCountry.setId(country.getId());
                    return repository.save(newCountry);
                });
    }


}
