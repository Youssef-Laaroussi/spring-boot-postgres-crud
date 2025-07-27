package com.youssef.bibliotheque.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionRepresentation {



    private String errorMessage;
    private String errorSource;
    private String operationId; // Nouveau champ
    private String dependency; // Nouveau champ
    private Set<String> validationErrors;









}
