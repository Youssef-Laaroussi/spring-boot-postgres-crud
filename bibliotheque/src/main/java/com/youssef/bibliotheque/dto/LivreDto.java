package com.youssef.bibliotheque.dto;

import com.youssef.bibliotheque.bean.Livre;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class LivreDto {


    private Integer id;

    @NotBlank(message = "Le titre ne doit pas être vide")
    @Size(min = 3, message = "Le titre doit contenir au moins 3 caractères")
    private String titre;

    @NotBlank(message = "L'auteur ne doit pas être vide")
    private String auteur;

    @Min(value = 1900, message = "L'année de sortie doit être supérieure ou égale à 1900")
    private int anneeSortie;

    private boolean disponible = true; // Valeur par défaut

    private Integer clientId; // Nouveau champ pour gérer la relation,Juste l'ID, pas l'objet complet



    public static LivreDto ToDto(Livre livre) {
        return LivreDto.builder()
                .id(livre.getId())
                .titre(livre.getTitre())
                .auteur(livre.getAuteur())
                .anneeSortie(livre.getAnneeSortie())
                .disponible(livre.isDisponible())
                .clientId(livre.getClient() != null ? livre.getClient().getId() : null)
                .build();

    }

    public static Livre ToEntity(LivreDto livre) {
        return Livre.builder()
                .id(livre.getId())
                .titre(livre.getTitre())
                .auteur(livre.getAuteur())
                .anneeSortie(livre.getAnneeSortie())
                .disponible(livre.isDisponible())
                .build();  // ERREUR: Livre n'a pas clientId mais livre contient un objet


    }






}