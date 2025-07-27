package com.youssef.bibliotheque.controller;


import com.youssef.bibliotheque.bean.Livre;
import com.youssef.bibliotheque.dto.LivreDto;
import com.youssef.bibliotheque.service.LivreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livre")
@RequiredArgsConstructor

public class LivreController {

    private final LivreService livreService;


    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody  LivreDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livreService.save(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(
            @PathVariable Integer id,
            @RequestBody  LivreDto dto) {
        if (!id.equals(dto.getId())) {
            throw new IllegalArgumentException("ID in URL does not match body");
        }
        return ResponseEntity.ok(livreService.update(dto));

    }






    @GetMapping("/")
    public ResponseEntity<List<LivreDto>> findAll() {
        return ResponseEntity.ok(livreService.findAll());
    }

   @GetMapping("/{id}")
    public ResponseEntity<LivreDto> findById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(livreService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Integer id) {
        livreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }















}
