package com.santanarscs.api.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.santanarscs.api.models.ClientModel;
import com.santanarscs.api.repositories.IClientsRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {
  
  final IClientsRepository clientsRepository;

  public ClientsService(IClientsRepository clientsRepository) {
    this.clientsRepository = clientsRepository;
  }

  @Transactional
  public ClientModel save(ClientModel clientModel) {
    return clientsRepository.save(clientModel);
  }

  public Page<ClientModel> findAll(Pageable pageable) {
    return clientsRepository.findAll(pageable);
  }

  public Optional<ClientModel> findById(UUID id) {
    return clientsRepository.findById(id);
  }

  @Transactional
  public void delete(ClientModel clientModel) {
    clientsRepository.delete(clientModel);
  }


}
