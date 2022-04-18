package com.santanarscs.api.repositories;

import java.util.UUID;

import com.santanarscs.api.models.CarModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarsRepository extends JpaRepository<CarModel, UUID> {
  
}
