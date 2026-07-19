package com.vitig.car_rent.service.contract;

import com.vitig.car_rent.data.dto.brand_dto.BrandCreateDto;
import com.vitig.car_rent.data.dto.brand_dto.BrandFetchDto;
import com.vitig.car_rent.data.dto.brand_dto.BrandUpdateDto;

import java.util.List;

public interface BrandService {
    List<BrandFetchDto> getAllBrands();
    BrandFetchDto getBrandById(Long id);
    BrandFetchDto createBrand(BrandCreateDto brandCreateDto);
    BrandFetchDto updateBrand(Long id, BrandUpdateDto brandUpdateDto);
    void deleteBrand(Long id);
}
