package com.youssef.bibliotheque.dto;


import com.youssef.bibliotheque.bean.Client;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ClientDto {

    private Integer id;

    @NotBlank(message = "Le nom ne doit pas être vide")
    private String nom;

    @NotNull(message = "l'email ne doit pas etre vide")
    @NotEmpty(message = "l'email ne doit pas etre vide")
    @NotBlank(message = "l'email ne doit pas etre vide")
    @Email(message = "L'email n'est pas conforme")
    @Column(unique = true)
    private String email;

public  static ClientDto ToDto(Client client) {
    return ClientDto.builder()
            .id(client.getId())
            .nom(client.getNom())
            .email(client.getEmail())
            .build();

}

public static Client ToEntity(ClientDto clientDto) {
    return Client.builder()
            .id(clientDto.getId())
            .nom(clientDto.getNom())
            .email(clientDto.getEmail())
            .build();

}





}
