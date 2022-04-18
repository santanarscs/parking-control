package com.santanarscs.api.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.santanarscs.api.models.CarModel;
import com.santanarscs.api.repositories.ICarsRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarsService {
  
  final ICarsRepository carsRepository;

  public CarsService(ICarsRepository carsRepository) {
    this.carsRepository = carsRepository;
  }

  @Transactional
  public CarModel save(CarModel carModel) {
    return carsRepository.save(carModel);
  }

  public Page<CarModel> findAll(Pageable pageable) {
    return carsRepository.findAll(pageable);
  }

  public Optional<CarModel> findById(UUID id) {
    return carsRepository.findById(id);
  }

  @Transactional
  public void delete(CarModel carModel) {
    carsRepository.delete(carModel);
  }

  


  
}
