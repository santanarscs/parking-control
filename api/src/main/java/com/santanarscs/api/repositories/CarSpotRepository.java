package com.santanarscs.api.repositories;

import java.util.UUID;

import com.santanarscs.api.models.CarSpot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarSpotRepository  extends JpaRepository<CarSpot, UUID> {
  
}
