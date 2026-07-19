package com.vitig.car_rent.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String plate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Model model;
}
