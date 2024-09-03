package com.example.person_service.service;

import com.example.common.AddressDto;
import com.example.person_service.entity.Address;
import com.example.person_service.mapper.AddressMapperImpl;
import com.example.person_service.repository.AddressRepository;
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

import static com.example.person_service.util.DataTestUtil.getAddressDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {


    @Spy
    AddressMapperImpl mapper;
    @Mock
    AddressRepository repository;
    @InjectMocks
    AddressService service;

    @Test
    void createAddress() {
        AddressDto addressDto = getAddressDto(LocalDateTime.now());
        Mockito.when(mapper.map(any(AddressDto.class))).thenCallRealMethod();
        Address addressFromDto = mapper.map(addressDto);
        Mockito.when(repository.save(any(Address.class))).thenReturn(Mono.just(addressFromDto));

        Mono<Address> address = service.createAddress(addressDto);

        StepVerifier.create(address)
                .assertNext( addr -> {
                    assertEquals(addr.getAddress(), addressFromDto.getAddress());
                })
                .verifyComplete();
    }

    @Test
    void getAddress() {
        Address addressFromDB = DataTestUtil.getAddress(LocalDateTime.now());
        Mockito.when(repository.findById(any(UUID.class))).thenReturn(Mono.just(addressFromDB));

        Mono<Address> address = service.getAddress(UUID.randomUUID());

        StepVerifier.create(address)
                .assertNext( addr -> {
                    assertEquals(addr.getAddress(), addressFromDB.getAddress());
                })
                .verifyComplete();
    }

    @Test
    void updateAddress() {
        Address addressFromDB = DataTestUtil.getAddress(LocalDateTime.now());
        AddressDto newAddressDto = getAddressDto(LocalDateTime.now());
        Address addressAfterSave = DataTestUtil.getAddress(LocalDateTime.now());
        addressAfterSave.setAddress(newAddressDto.getAddress());
        Mockito.when(mapper.map(any(AddressDto.class))).thenCallRealMethod();
        Mockito.when(repository.findById(any(UUID.class))).thenReturn(Mono.just(addressFromDB));
        Mockito.when(repository.save(any(Address.class))).thenReturn(Mono.just(addressAfterSave));

        Mono<Address> address = service.updateAddress(addressFromDB.getId(), newAddressDto);

        StepVerifier.create(address)
                .assertNext( addr -> {
                    assertEquals(addr.getAddress(), newAddressDto.getAddress());
                })
                .verifyComplete();
    }

    @Test
    void deleteAddress() {
        Address addressFromDB = DataTestUtil.getAddress(LocalDateTime.now());
        Mockito.when(repository.deleteById(any(UUID.class))).thenReturn(Mono.empty());

        Mono<Void> voidMono = service.deleteAddress(addressFromDB.getId());

        StepVerifier.create(voidMono)
                .verifyComplete();
    }
}