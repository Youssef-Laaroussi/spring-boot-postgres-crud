package com.youssef.bibliotheque.service.impl;

import com.youssef.bibliotheque.bean.Client;
import com.youssef.bibliotheque.dao.ClientRepositroy;
import com.youssef.bibliotheque.dao.LivreRepository;
import com.youssef.bibliotheque.dto.ClientDto;
import com.youssef.bibliotheque.exceptions.OperationNonPermittedException;
import com.youssef.bibliotheque.service.ClientService;
import com.youssef.bibliotheque.validators.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ClientServiceImpl  implements ClientService  {

    private final ObjectValidator<ClientDto> validator;
    private final ClientRepositroy clientRepositroy;






    @Override
    public Integer save(ClientDto dto) {
        validator.validate(dto);

        if (dto.getId() != null) {
            throw new IllegalArgumentException("ID should not be provided for creation");
        }

        clientRepositroy.findByEmail(dto.getEmail())
                .ifPresent(c -> {
                    throw new OperationNonPermittedException("Email already exists");
                });

        return clientRepositroy.save(ClientDto.ToEntity(dto)).getId();
    }


    //update
    @Override
    public Integer update(ClientDto dto) {
        validator.validate(dto);

        if (dto.getId() == null) {
            throw new IllegalArgumentException("ID is required for update");
        }

        Client existing = clientRepositroy.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        clientRepositroy.findByEmail(dto.getEmail())
                .ifPresent(c -> {
                    if (!c.getId().equals(dto.getId())) {
                        throw new OperationNonPermittedException("Email used by another client");
                    }
                });

        existing.setNom(dto.getNom());
        existing.setEmail(dto.getEmail());

        return clientRepositroy.save(existing).getId();
    }



































    @Override
    public List<ClientDto> findAll() {

        return clientRepositroy.findAll()
                .stream()
                .map(ClientDto::ToDto)
                .collect(Collectors.toList());
    }


    @Override
    public ClientDto findById(Integer id) {

       return clientRepositroy.findById(id)
               .map(ClientDto::ToDto)
               .orElseThrow(()->new EntityNotFoundException("No Client was found with the provided ID"+id));
    }


    @Override
    public void deleteById(Integer id) {
        if(!clientRepositroy.existsById(id)){
            throw new EntityNotFoundException("No Client was found with the provided ID"+id);
        }
        clientRepositroy.deleteById(id);

    }
















}
