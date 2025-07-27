package com.youssef.bibliotheque.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OperationNonPermittedException extends RuntimeException{


private final String errorMsg;

private final String operationId;

private final String source;

private final String dependency;


    // Constructeur simplifié avec valeurs par défaut
    public OperationNonPermittedException(String errorMsg) {
        this(errorMsg, "N/A", "N/A", "N/A");
    }














}
