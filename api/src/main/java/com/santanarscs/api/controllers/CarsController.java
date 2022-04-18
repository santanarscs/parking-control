package com.santanarscs.api.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.santanarscs.api.dtos.CarDto;
import com.santanarscs.api.models.CarModel;
import com.santanarscs.api.service.CarsService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class CarsController {

  final CarsService carsService;

  static final String NOT_FOUND_MESSAGE = "Car not found";

  public CarsController(CarsService carsService) {
    this.carsService = carsService;
  }


  @PostMapping
  public ResponseEntity<Object> save(@RequestBody @Valid CarDto carDto) {
    var carModel = new CarModel();
    BeanUtils.copyProperties(carDto, carModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(carsService.save(carModel));
  }

  @GetMapping()
  public ResponseEntity<Page<CarModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
      return ResponseEntity.status(HttpStatus.OK).body(carsService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getOne(@PathVariable(value = "id") UUID id) {
    Optional<CarModel> carModelOptional = carsService.findById(id);
    if(!carModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
    }
    return ResponseEntity.status(HttpStatus.OK).body(carModelOptional.get());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateCar(@PathVariable(value = "id")UUID id,  @RequestBody @Valid CarDto carDto) {
    Optional<CarModel> carModelOptional = carsService.findById(id);
    if(!carModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(NOT_FOUND_MESSAGE);
    }
    var carModel = new CarModel();
    BeanUtils.copyProperties(carDto, carModel);
    carModel.setId(carModelOptional.get().getId());
    return ResponseEntity.status(HttpStatus.OK).body(carsService.save(carModel));

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
    Optional<CarModel> carModelOptional = carsService.findById(id);
    if(!carModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(NOT_FOUND_MESSAGE);
    }
    carsService.delete(carModelOptional.get());
    return ResponseEntity.status(HttpStatus.OK).body("Car was deleted");
  }
  
}
