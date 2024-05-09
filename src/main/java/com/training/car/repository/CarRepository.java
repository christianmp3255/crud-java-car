package com.training.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.car.entity.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Long> {}
