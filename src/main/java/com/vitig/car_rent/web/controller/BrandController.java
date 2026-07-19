package com.vitig.car_rent.web.controller;

import com.vitig.car_rent.data.dto.brand_dto.BrandCreateDto;
import com.vitig.car_rent.data.dto.brand_dto.BrandFetchDto;
import com.vitig.car_rent.data.dto.brand_dto.BrandUpdateDto;
import com.vitig.car_rent.service.contract.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public List<BrandFetchDto> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    public BrandFetchDto getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }

    @PostMapping
    public BrandFetchDto createBrand(@Valid @RequestBody BrandCreateDto brandFetchDto) {
        return this.brandService.createBrand(brandFetchDto);
    }

    @PutMapping("/{id}")
    public BrandFetchDto updateBrand(@PathVariable Long id, @Valid @RequestBody BrandUpdateDto brandUpdateDto) {
        return this.brandService.updateBrand(id, brandUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Long id) {
        this.brandService.deleteBrand(id);
    }
}
