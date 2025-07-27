package com.youssef.bibliotheque.controller;

import com.youssef.bibliotheque.dto.ClientDto;
import com.youssef.bibliotheque.dto.LivreDto;
import com.youssef.bibliotheque.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody  ClientDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(dto));
    }




    @GetMapping("/")
    public ResponseEntity<List<ClientDto>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Integer id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



    @PutMapping("/{id}") // RESTful: PUT sur la ressource spécifique
    public ResponseEntity<Integer> update(
            @PathVariable Integer id,
            @RequestBody ClientDto dto) {
        if (!id.equals(dto.getId())) {
            throw new IllegalArgumentException("URL ID and body ID mismatch");
        }
        return ResponseEntity.ok(clientService.update(dto));
    }













}
