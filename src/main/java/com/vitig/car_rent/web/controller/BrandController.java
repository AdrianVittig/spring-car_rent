package com.vitig.car_rent.web.controller;

import com.vitig.car_rent.data.dto.brand_dto.BrandCreateDto;
import com.vitig.car_rent.data.dto.brand_dto.BrandFetchDto;
import com.vitig.car_rent.data.dto.brand_dto.BrandUpdateDto;
import com.vitig.car_rent.service.contract.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BrandFetchDto> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BrandFetchDto getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrandFetchDto createBrand(@Valid @RequestBody BrandCreateDto brandFetchDto) {
        return this.brandService.createBrand(brandFetchDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BrandFetchDto updateBrand(@PathVariable Long id, @Valid @RequestBody BrandUpdateDto brandUpdateDto) {
        return this.brandService.updateBrand(id, brandUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable Long id) {
        this.brandService.deleteBrand(id);
    }

    @PostMapping("/{brandId}/models/{modelId}")
    public void addModelToBrand(@PathVariable Long brandId, @PathVariable Long modelId) {
        this.brandService.addModelToBrand(brandId, modelId);
    }
}
