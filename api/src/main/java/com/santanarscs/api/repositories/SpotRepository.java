package com.santanarscs.api.repositories;

import java.util.UUID;

import com.santanarscs.api.models.Spot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, UUID>  {
  
}
