package com.santanarscs.api.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import com.santanarscs.api.dtos.CarSpotDto;
import com.santanarscs.api.models.CarSpot;
import com.santanarscs.api.service.CarSpotService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cars_spots")
public class CarSpotController {

  final CarSpotService carSpotService;

  public CarSpotController(CarSpotService carSpotService) {
    this.carSpotService = carSpotService;
  }

  @PostMapping
  public CarSpot create(@RequestBody @Valid CarSpotDto carSpotDto) throws NameNotFoundException {
    return carSpotService.save(carSpotDto);
  }

  @GetMapping()
  public Page<CarSpot> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
      return carSpotService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public Optional<CarSpot> find(@PathVariable(value = "id") UUID id) {
    return carSpotService.findById(id);
  }
  
}
