package com.santanarscs.api.service;

import java.util.Optional;
import java.util.UUID;

import javax.naming.NameNotFoundException;
import javax.transaction.Transactional;

import com.santanarscs.api.dtos.CarDto;
import com.santanarscs.api.models.Car;
import com.santanarscs.api.models.Client;
import com.santanarscs.api.repositories.CarRepository;
import com.santanarscs.api.repositories.ClientRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarService {
  
  final CarRepository carRepository;
  final ClientRepository clientRepository;

  public CarService(CarRepository carRepository, ClientRepository clientRepository) {
    this.carRepository = carRepository;
    this.clientRepository = clientRepository;
  }

  @Transactional
  public Car save(CarDto carDto) throws NameNotFoundException {
    Optional<Client> clientFound = clientRepository.findById(carDto.getClientId());
    if(!clientFound.isPresent()) {
      throw new NameNotFoundException("Client not found!");
    }
    var car = new Car();
    car.setClient(clientFound.get());
    BeanUtils.copyProperties(carDto, car);
    return carRepository.save(car);
  }

  public Page<Car> findAll(Pageable pageable) {
    return carRepository.findAll(pageable);
  }

  public Optional<Car> findById(UUID id) {
    return carRepository.findById(id);
  }

  @Transactional
  public void delete(UUID id) throws NameNotFoundException {
    Optional<Car> car = this.findById(id);
    if(!car.isPresent()){
      throw new NameNotFoundException("Car not found!");
    }
    carRepository.delete(car.get());
  }

  @Transactional
  public Car update(UUID id, CarDto carDto) throws NameNotFoundException {
    Optional<Car> carFound = this.findById(id);
    if(!carFound.isPresent()){
      throw new NameNotFoundException("Car not found!");
    }
    var car = new Car();
    BeanUtils.copyProperties(carDto, car);
    car.setId(carFound.get().getId());
    return carRepository.save(car);
  }
}
