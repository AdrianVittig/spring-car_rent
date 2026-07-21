package com.vitig.car_rent.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rent extends BaseEntity{
    @OneToOne
    private Car car;
    @ManyToOne
    private Customer customer;
    private LocalDateTime rentDate;
    private LocalDateTime returnDate;
    private BigDecimal totalPrice;
}
