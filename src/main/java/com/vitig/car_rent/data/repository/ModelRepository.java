package com.vitig.car_rent.data.repository;

import com.vitig.car_rent.data.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
