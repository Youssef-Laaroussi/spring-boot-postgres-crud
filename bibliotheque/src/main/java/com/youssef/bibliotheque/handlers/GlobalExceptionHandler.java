package com.youssef.bibliotheque.handlers;




import com.youssef.bibliotheque.exceptions.ObjectValidationException;
import com.youssef.bibliotheque.exceptions.OperationNonPermittedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;


@RestControllerAdvice

public class GlobalExceptionHandler {





    @ExceptionHandler({ObjectValidationException.class})
    public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("Object not valid exception has occurred")
                .errorSource(exception.getViolationSource())
                .validationErrors(exception.getViolations())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(representation);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(EntityNotFoundException exception) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(representation);
    }


/*
  @ExceptionHandler(OperationNonPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(OperationNonPermittedException exception) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getErrorMsg())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(representation);
    }

*/

    @ExceptionHandler(OperationNonPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(OperationNonPermittedException exception) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getErrorMsg())
                .errorSource(exception.getSource()) // Ajouté
                .operationId(exception.getOperationId()) // Ajouté
                .dependency(exception.getDependency()) // Ajouté
                .build();
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(representation);
    }















  @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException() {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("A client already exists with the provided Email")
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }







/* moi qui comenter ça

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionRepresentation> handleDisableException() {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("You cannot access your account because it is not yet activated")
                .build();
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(representation);
    }



    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionRepresentation> handleBadCredentialsException() {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("Your email and / or password is incorrect")
                .build();
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(representation);
    }

    * moi /




















































 */

























    /*



@ExceptionHandler(ObjectValidationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception) {
    ExceptionRepresentation representation=ExceptionRepresentation.builder()
            .errorMessage("Object not valid exception has occured")
            .errorSource(exception.getViolationSource())
            .validationErrors(exception.getViolations())
            .build();
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(representation);

}



@ExceptionHandler(EntityNotFoundException.class)
public ResponseEntity<ExceptionRepresentation> handelException(EntityNotFoundException exception) {
    ExceptionRepresentation representation=ExceptionRepresentation.builder()
            .errorMessage(exception.getMessage())
            .build();
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(representation);
}




    @ExceptionHandler(OperationNonPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handelException(OperationNonPermittedException exception) {
        ExceptionRepresentation representation=ExceptionRepresentation.builder()
                .errorMessage(exception.getErrorMsg())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(representation);
    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException() {
    ExceptionRepresentation representation=ExceptionRepresentation.builder()
            .errorMessage("A user already exists with the provided Email")
            .build();
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(representation);
    }











/*

 */














}