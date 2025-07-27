package com.youssef.bibliotheque.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.core.SpringVersion;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity

public class Livre extends AbstractEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livre_seq")
    @SequenceGenerator(
            name = "livre_seq",
            sequenceName = "livre_id_seq",  // Séquence dédiée
            allocationSize = 1
    )
    private Integer id;

    private String titre;

    private String auteur;

    private int anneeSortie;

    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;









}
