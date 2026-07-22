package com.vitig.car_rent.web.controller;


import com.vitig.car_rent.data.dto.rent_dto.RentCreateDto;
import com.vitig.car_rent.data.dto.rent_dto.RentFetchDto;
import com.vitig.car_rent.data.dto.rent_dto.RentUpdateDto;
import com.vitig.car_rent.service.contract.RentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rents")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RentFetchDto> getAllRents() {
        return rentService.getAllRents();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentFetchDto getRentById(@PathVariable Long id) {
        return rentService.getRentById(id);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public List<RentFetchDto> getRentByCustomerId() {
        return rentService.getMyRents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentFetchDto createRent(@Valid @RequestBody RentCreateDto rentCreateDto) {
        return rentService.createRent(rentCreateDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentFetchDto updateRent(@PathVariable Long id, @Valid @RequestBody RentUpdateDto rentUpdateDto) {
        return rentService.updateRent(id, rentUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRent(@PathVariable Long id) {
        rentService.deleteRent(id);
    }
}
