package com.vitig.car_rent.data.dto.car_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarCreateDto {
    @NotBlank
    private String plate;
    @NotNull
    private Long modelId;
}
