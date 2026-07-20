package com.vitig.car_rent.web.dto;

import com.vitig.car_rent.data.util.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String username;
    private String token;
    private UserRole role;
}
