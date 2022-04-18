package com.santanarscs.api.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.santanarscs.api.dtos.ClientDto;
import com.santanarscs.api.models.ClientModel;
import com.santanarscs.api.service.ClientsService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/clients")
public class ClientsController {
  final ClientsService clientsService;

  static final String NOT_FOUND_MESSAGE = "Client not found";
  
  public ClientsController(ClientsService clientsService) {
    this.clientsService = clientsService;
  }

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody @Valid ClientDto clientDto) {
    var clientModel = new ClientModel();
    BeanUtils.copyProperties(clientDto, clientModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(clientsService.save(clientModel));
  }

  @GetMapping()
  public ResponseEntity<Page<ClientModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
      return ResponseEntity.status(HttpStatus.OK).body(clientsService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getOne(@PathVariable(value = "id") UUID id) {
    Optional<ClientModel> clientModelOptional = clientsService.findById(id);
    if(!clientModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
    }
    return ResponseEntity.status(HttpStatus.OK).body(clientModelOptional.get());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable(value = "id")UUID id,  @RequestBody @Valid ClientDto clientDto) {
    Optional<ClientModel> clientModelOptional = clientsService.findById(id);
    if(!clientModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(NOT_FOUND_MESSAGE);
    }
    var clientModel = new ClientModel();
    BeanUtils.copyProperties(clientDto, clientModel);
    clientModel.setId(clientModelOptional.get().getId());
    return ResponseEntity.status(HttpStatus.OK).body(clientsService.save(clientModel));

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
    Optional<ClientModel> clientModelOptional = clientsService.findById(id);
    if(!clientModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(NOT_FOUND_MESSAGE);
    }
    clientsService.delete(clientModelOptional.get());
    return ResponseEntity.status(HttpStatus.OK).body("Client was deleted");
  }
  
}
