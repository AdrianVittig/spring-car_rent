package com.vitig.car_rent.data.dto.brand_dto;

import com.vitig.car_rent.data.util.CarBrand;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandCreateDto {
    @NotNull
    private CarBrand brand;
}
