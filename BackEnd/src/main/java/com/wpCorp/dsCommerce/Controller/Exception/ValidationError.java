package com.wpCorp.dsCommerce.Controller.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError extends StandartError {

    private Set<FieldMessage> fieldsErros = new HashSet<>();

    public void addFields(String fieldName, String message) {
        fieldsErros.add(new FieldMessage(fieldName, message));
    }
}
