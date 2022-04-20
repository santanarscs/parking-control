package com.santanarscs.api.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import com.santanarscs.api.dtos.ClientDto;
import com.santanarscs.api.models.Client;
import com.santanarscs.api.service.ClientService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
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
public class ClientController {
  final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping
  public Client create(@RequestBody @Valid ClientDto clientDto) {
    return clientService.save(clientDto);
  }

  @GetMapping()
  public Page<Client> getAll(
      @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
    return clientService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public Optional<Client> find(@PathVariable(value = "id") UUID id) {
    return clientService.findById(id);
  }

  @PutMapping("/{id}")
  public Client update(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClientDto clientDto)
      throws NameNotFoundException {
    return clientService.update(id, clientDto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable(value = "id") UUID id) throws NameNotFoundException {
    clientService.delete(id);
  }

}
