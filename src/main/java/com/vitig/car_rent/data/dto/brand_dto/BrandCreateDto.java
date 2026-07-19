package com.vitig.car_rent.data.dto.brand_dto;

import com.vitig.car_rent.data.entity.Model;
import com.vitig.car_rent.data.util.CarBrand;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandCreateDto {
    @NotNull
    private CarBrand brand;
    @NotNull
    private Set<Model> models;
}
