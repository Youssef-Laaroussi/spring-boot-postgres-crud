package com.youssef.bibliotheque.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity

public class Client extends AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(
            name = "client_seq",
            sequenceName = "client_id_seq",  // Séquence dédiée
            allocationSize = 1
    )
    private Integer id;

    private String nom;

    private String email;

    @OneToMany(mappedBy = "client")
    private List<Livre> livres;























}
