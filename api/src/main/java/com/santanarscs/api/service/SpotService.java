package com.santanarscs.api.service;

import java.util.Optional;
import java.util.UUID;

import javax.naming.NameNotFoundException;
import javax.transaction.Transactional;

import com.santanarscs.api.dtos.SpotDto;
import com.santanarscs.api.models.Spot;
import com.santanarscs.api.repositories.SpotRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SpotService {
  
  final SpotRepository spotRepository;

  public SpotService(SpotRepository spotRepository) {
    this.spotRepository = spotRepository;
  }

  @Transactional
  public Spot save(SpotDto spotDto) {
    var spot = new Spot();
    BeanUtils.copyProperties(spotDto, spot);
    return spotRepository.save(spot);
  }

  public Page<Spot> findAll(Pageable pageable) {
    return spotRepository.findAll(pageable);
  }

  public Optional<Spot> findById(UUID id) {
    return spotRepository.findById(id);
  }

  @Transactional
  public void delete(UUID id) throws NameNotFoundException {
    Optional<Spot> spot = this.findById(id);
    if(!spot.isPresent()){
      throw new NameNotFoundException("Spot not found!");
    }
    spotRepository.delete(spot.get());
  }

  @Transactional
  public Spot update(UUID id, SpotDto spotDto) throws NameNotFoundException {
    Optional<Spot> spotFound = this.findById(id);
    if(!spotFound.isPresent()){
      throw new NameNotFoundException("Spot not found!");
    }
    var spot = new Spot();
    BeanUtils.copyProperties(spotDto, spot);
    spot.setId(spotFound.get().getId());
    return spotRepository.save(spot);
  }
}
