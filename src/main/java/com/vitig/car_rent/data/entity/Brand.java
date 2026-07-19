package com.vitig.car_rent.data.entity;

import com.vitig.car_rent.data.util.CarBrand;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "brands")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private CarBrand brand;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    private Set<Model> model;
}
