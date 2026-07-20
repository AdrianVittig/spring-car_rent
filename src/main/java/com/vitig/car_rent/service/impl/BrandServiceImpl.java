package com.vitig.car_rent.service.impl;

import com.vitig.car_rent.config.util.ModelMapperUtil;
import com.vitig.car_rent.data.dto.brand_dto.BrandCreateDto;
import com.vitig.car_rent.data.dto.brand_dto.BrandFetchDto;
import com.vitig.car_rent.data.dto.brand_dto.BrandUpdateDto;
import com.vitig.car_rent.data.dto.model_dto.ModelFetchDto;
import com.vitig.car_rent.data.entity.Brand;
import com.vitig.car_rent.data.entity.Model;
import com.vitig.car_rent.data.exception.ObjectNotFoundException;
import com.vitig.car_rent.data.repository.BrandRepository;
import com.vitig.car_rent.service.contract.BrandService;
import com.vitig.car_rent.service.contract.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelService modelService;
    private final ModelMapperUtil modelMapperUtil;

    @Override
    public List<BrandFetchDto> getAllBrands() {
        return this.brandRepository.findAll().stream()
                .map(brand -> modelMapperUtil.map(brand, BrandFetchDto.class))
                .toList();
    }

    @Override
    public BrandFetchDto getBrandById(Long id) {
        return modelMapperUtil.map(this.brandRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Brand not found with id: " + id + "!")
        ), BrandFetchDto.class);
    }

    @Override
    @Transactional
    public BrandFetchDto createBrand(BrandCreateDto brandCreateDto) {
        Brand brand = modelMapperUtil.map(brandCreateDto, Brand.class);
        return modelMapperUtil.map(this.brandRepository.save(brand), BrandFetchDto.class);
    }

    @Override
    @Transactional
    public BrandFetchDto updateBrand(Long id, BrandUpdateDto brandUpdateDto) {
        Brand brand = this.brandRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Brand not found with id: " + id + "!")
        );
        brand.setBrand(brandUpdateDto.getBrand());
        return modelMapperUtil.map(this.brandRepository.save(brand), BrandFetchDto.class);
    }

    @Override
    @Transactional
    public void deleteBrand(Long id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Brand not found with id: " + id + "!")
        );
        this.brandRepository.delete(brand);
    }

    @Override
    public void addModelToBrand(Long brandId, Long modelId) {
        Model model = modelMapperUtil.map(this.modelService.getModelById(modelId), Model.class);
        Brand brand = modelMapperUtil.map(this.brandRepository.findById(brandId), Brand.class);

        model.setBrand(brand);
    }
}
