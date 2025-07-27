package com.youssef.bibliotheque.validators;


import com.youssef.bibliotheque.exceptions.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator<T> {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate(T objectToValidate) {
       Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
       if (!violations.isEmpty()) {
           Set<String> errorMessages = violations.stream()
                   .map(ConstraintViolation::getMessage)
                   .collect(Collectors.toSet());

           throw new ObjectValidationException(errorMessages,objectToValidate.getClass().getName());
       }






    }

























}
