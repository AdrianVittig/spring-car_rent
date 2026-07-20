package com.vitig.car_rent.web.controller;

import com.vitig.car_rent.service.contract.UserService;
import com.vitig.car_rent.web.dto.LoginRequestDto;
import com.vitig.car_rent.web.dto.LoginResponseDto;
import com.vitig.car_rent.web.dto.RegisterRequestDto;
import com.vitig.car_rent.web.dto.RegisterResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return userService.login(loginRequestDto);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponseDto register(@Valid @RequestBody RegisterRequestDto registerRequestDto){
        return userService.register(registerRequestDto);
    }
}
