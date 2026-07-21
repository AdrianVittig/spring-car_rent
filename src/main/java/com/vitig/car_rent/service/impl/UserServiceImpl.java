package com.vitig.car_rent.service.impl;

import com.vitig.car_rent.data.entity.Customer;
import com.vitig.car_rent.data.entity.User;
import com.vitig.car_rent.data.exception.UserAlreadyExistsException;
import com.vitig.car_rent.data.repository.CustomerRepository;
import com.vitig.car_rent.data.repository.UserRepository;
import com.vitig.car_rent.data.util.JwtUtil;
import com.vitig.car_rent.data.util.UserRole;
import com.vitig.car_rent.service.contract.UserService;
import com.vitig.car_rent.web.dto.LoginRequestDto;
import com.vitig.car_rent.web.dto.LoginResponseDto;
import com.vitig.car_rent.web.dto.RegisterRequestDto;
import com.vitig.car_rent.web.dto.RegisterResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        User user = userRepository.findByUsername(registerRequestDto.getUsername());
        if(user != null){
            throw new UserAlreadyExistsException("User already exists");
        }
        user = new User();
        Customer customer = new Customer();
        user.setUsername(registerRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        customer.setUser(user);
        userRepository.save(user);
        customerRepository.save(customer);
        RegisterResponseDto responseDto = new RegisterResponseDto();
        responseDto.setUsername(registerRequestDto.getUsername());

        return responseDto;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername()
                , loginRequestDto.getPassword());
        Authentication auth = this.authenticationManager.authenticate(authenticationToken);
        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setToken(jwtUtil.generateToken(auth.getName()));
        responseDto.setUsername(loginRequestDto.getUsername());
        return responseDto;
    }
}
