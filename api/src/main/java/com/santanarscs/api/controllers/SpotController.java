package com.santanarscs.api.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import com.santanarscs.api.dtos.SpotDto;
import com.santanarscs.api.models.Spot;
import com.santanarscs.api.service.SpotService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/spots")
public class SpotController {

  final SpotService spotService;

  public SpotController(SpotService spotService) {
    this.spotService = spotService;
  }
  
  @PostMapping
  public Spot create(@RequestBody @Valid SpotDto spotDto) {
    return spotService.save(spotDto);
  }

  @GetMapping()
  public Page<Spot> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
      return spotService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public Optional<Spot> find(@PathVariable(value = "id") UUID id) {
    return spotService.findById(id);
  }

  @PutMapping("/{id}")
  public Spot update(@PathVariable(value = "id")UUID id,  @RequestBody @Valid SpotDto spotDto) throws NameNotFoundException {
    return spotService.update(id, spotDto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable(value = "id") UUID id) throws NameNotFoundException {
    spotService.delete(id);
  }
  
}
