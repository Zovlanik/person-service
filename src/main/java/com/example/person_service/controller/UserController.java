package com.example.person_service.controller;

import com.example.common.UserDto;
import com.example.person_service.entity.User;
import com.example.person_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public Mono<User> createUser (@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }
}
