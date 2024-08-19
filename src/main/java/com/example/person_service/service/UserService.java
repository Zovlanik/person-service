package com.example.person_service.service;


import com.example.common.UserDto;
import com.example.person_service.entity.User;
import com.example.person_service.mapper.UserMapper;
import com.example.person_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;
    private final UserRepository repository;

    public Mono<User> createUser(UserDto userDto){
        return repository.save(mapper.map(userDto));
    }

}
