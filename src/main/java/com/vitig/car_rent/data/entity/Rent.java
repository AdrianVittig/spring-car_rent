package com.vitig.car_rent.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "rents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rent extends BaseEntity{
    @OneToOne
    private Car car;
    @OneToOne
    private Customer customer;
    private Long rentDate;
    private Long returnDate;
    private BigDecimal totalPrice;
}
