package com.youssef.bibliotheque.service.impl;

import com.youssef.bibliotheque.bean.Client;
import com.youssef.bibliotheque.bean.Livre;
import com.youssef.bibliotheque.dao.ClientRepositroy;
import com.youssef.bibliotheque.dao.LivreRepository;
import com.youssef.bibliotheque.dto.LivreDto;
import com.youssef.bibliotheque.service.LivreService;
import com.youssef.bibliotheque.validators.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LiverServiceImpl implements LivreService {

    private final ObjectValidator<LivreDto> validator;
    private final LivreRepository livreRepository;
    private final ClientRepositroy clientRepositroy;



    @Override
    public Integer save(LivreDto dto) {
        validator.validate(dto);

        if (dto.getId() != null) {
            throw new IllegalArgumentException("La création d'un livre ne doit pas inclure d'ID");
        }

        Livre livre = LivreDto.ToEntity(dto);

        // Vérification ET association du client
        if (dto.getClientId() != null) {
            Client client = clientRepositroy.findById(dto.getClientId())
                    .orElseThrow(() -> new EntityNotFoundException("Client non trouvé avec ID: " + dto.getClientId()));
            livre.setClient(client); // <- Ceci était manquant dans votre code
        }

        return livreRepository.save(livre).getId();
    }








    @Override
    public List<LivreDto> findAll() {
        return livreRepository.findAll()
                .stream()
                .map(LivreDto::ToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LivreDto findById(Integer id) {
        return livreRepository.findById(id)
                .map(LivreDto::ToDto)
                .orElseThrow(() -> new EntityNotFoundException("Livre non trouvé avec ID: " + id));
    }

    @Override
    public void deleteById(Integer id) {
        if (!livreRepository.existsById(id)) {
            throw new EntityNotFoundException("Impossible de supprimer - Livre non trouvé avec ID: " + id);
        }
        livreRepository.deleteById(id);
    }

    @Override
    public Integer update(LivreDto dto) {
        validator.validate(dto);

        Livre existing = livreRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Livre non trouvé avec ID: " + dto.getId()));

        // Vérification client si présent
        if (dto.getClientId() != null) {
            Client client = clientRepositroy.findById(dto.getClientId())
                    .orElseThrow(() -> new EntityNotFoundException("Client non trouvé avec ID: " + dto.getClientId()));
            existing.setClient(client);
        } else {
            existing.setClient(null);
        }

        existing.setTitre(dto.getTitre());
        existing.setAuteur(dto.getAuteur());
        existing.setAnneeSortie(dto.getAnneeSortie());
        existing.setDisponible(dto.isDisponible());

        return livreRepository.save(existing).getId();
    }








}





























