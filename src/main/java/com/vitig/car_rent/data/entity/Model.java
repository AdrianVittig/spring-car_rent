package com.vitig.car_rent.data.entity;

import com.vitig.car_rent.data.util.CarColor;
import com.vitig.car_rent.data.util.CarFuel;
import com.vitig.car_rent.data.util.CarModel;
import com.vitig.car_rent.data.util.CarType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "models")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Model extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private CarModel model;
    private Integer year;
    @Enumerated(EnumType.STRING)
    private CarColor color;
    @Enumerated(EnumType.STRING)
    private CarType type;
    private Integer seats;
    private Integer doors;
    @Enumerated(EnumType.STRING)
    private CarFuel fuel;
    private Integer minimalAge;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;
}
