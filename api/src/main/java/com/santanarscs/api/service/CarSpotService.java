package com.santanarscs.api.service;

import java.util.Optional;
import java.util.UUID;

import javax.naming.NameNotFoundException;
import javax.transaction.Transactional;

import com.santanarscs.api.dtos.CarSpotDto;
import com.santanarscs.api.models.Car;
import com.santanarscs.api.models.CarSpot;
import com.santanarscs.api.models.Spot;
import com.santanarscs.api.repositories.CarRepository;
import com.santanarscs.api.repositories.CarSpotRepository;
import com.santanarscs.api.repositories.SpotRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarSpotService {
  final CarRepository carRepository;
  final SpotRepository spotRepository;
  final CarSpotRepository carSpotRepository;
  
  public CarSpotService(CarRepository carRepository, SpotRepository spotRepository, CarSpotRepository carSpotRepository) {
    this.carRepository = carRepository;
    this.spotRepository = spotRepository;
    this.carSpotRepository = carSpotRepository;
  }

  @Transactional
  public CarSpot save(CarSpotDto carSpotDto) throws NameNotFoundException {
    
    Optional<Car> carFound = carRepository.findById(carSpotDto.getCarId());
    if(!carFound.isPresent()) {
      throw new NameNotFoundException("Car not found!");
    }

    Optional<Spot> spotFound = spotRepository.findById(carSpotDto.getSpotId());
    if(!spotFound.isPresent()) {
      throw new NameNotFoundException("Spot not found!");
    }

    var carSpot = new CarSpot();
    carSpot.setCar(carFound.get());
    carSpot.setSpot(spotFound.get());
    return carSpotRepository.save(carSpot);
  }

  public Page<CarSpot> findAll(Pageable pageable) {
    return carSpotRepository.findAll(pageable);
  }

  public Optional<CarSpot> findById(UUID id) {
    return carSpotRepository.findById(id);
  }

  
}
