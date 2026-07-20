package com.vitig.car_rent.service.contract;

import com.vitig.car_rent.web.dto.LoginRequestDto;
import com.vitig.car_rent.web.dto.LoginResponseDto;
import com.vitig.car_rent.web.dto.RegisterRequestDto;
import com.vitig.car_rent.web.dto.RegisterResponseDto;

public interface UserService {
    RegisterResponseDto register(RegisterRequestDto registerRequestDto);
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
