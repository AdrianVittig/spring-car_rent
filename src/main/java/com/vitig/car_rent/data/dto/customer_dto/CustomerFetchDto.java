package com.vitig.car_rent.data.dto.customer_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFetchDto {
    private Long id;
    private String name;
    private List<Long> rentIds;
}
