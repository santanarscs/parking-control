package com.santanarscs.api.service;

import java.util.Optional;
import java.util.UUID;

import javax.naming.NameNotFoundException;
import javax.transaction.Transactional;

import com.santanarscs.api.dtos.ClientDto;
import com.santanarscs.api.models.Client;
import com.santanarscs.api.repositories.ClientRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  
  final ClientRepository clientRepository;

  public ClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Transactional
  public Client save(ClientDto clientDto) {
    var client = new Client();
    BeanUtils.copyProperties(clientDto, client);
    return clientRepository.save(client);
  }

  public Page<Client> findAll(Pageable pageable) {
    return clientRepository.findAll(pageable);
  }

  public Optional<Client> findById(UUID id) {
    return clientRepository.findById(id);
  }

  @Transactional
  public void delete(UUID id) throws NameNotFoundException {
    Optional<Client> client = this.findById(id);
    if(!client.isPresent()){
      throw new NameNotFoundException("Client not found!");
    }
    clientRepository.delete(client.get());
  }

  @Transactional
  public Client update(UUID id, ClientDto clientDto) throws NameNotFoundException {
    Optional<Client> clientFound = this.findById(id);
    if(!clientFound.isPresent()){
      throw new NameNotFoundException("Client not found!");
    }
    var client = new Client();
    BeanUtils.copyProperties(clientDto, client);
    client.setId(clientFound.get().getId());
    return clientRepository.save(client);
  }


}
