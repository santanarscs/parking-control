package com.santanarscs.api.repositories;

import java.util.UUID;

import com.santanarscs.api.models.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
  
}
