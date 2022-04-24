package org.toxicgames.memology.common.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ValidationException extends RuntimeException {

    @Getter
    private final List<ObjectError> errors;

    public ValidationException(Collection<? extends ObjectError> errors) {
        this.errors = new ArrayList<>(errors);
    }

}
