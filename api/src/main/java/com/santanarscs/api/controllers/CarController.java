package com.santanarscs.api.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import com.santanarscs.api.dtos.CarDto;
import com.santanarscs.api.models.Car;
import com.santanarscs.api.service.CarService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cars")
public class CarController {

  final CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  @PostMapping
  public Car save(@RequestBody @Valid CarDto carDto) throws NameNotFoundException{
    return carService.save(carDto);
  }

  @GetMapping()
  public Page<Car> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
      return carService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public Optional<Car> getOne(@PathVariable(value = "id") UUID id) {
    return carService.findById(id);
  }

  @PutMapping("/{id}")
  public Car updateCar(@PathVariable(value = "id")UUID id,  @RequestBody @Valid CarDto carDto) throws NameNotFoundException {
    return carService.update(id, carDto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable(value = "id") UUID id) throws NameNotFoundException  {
    carService.delete(id);
  }
  
}
