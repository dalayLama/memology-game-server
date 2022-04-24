package org.toxicgames.memology.common.components.errors.impls.providers;

import org.springframework.stereotype.Component;
import org.toxicgames.memology.common.components.errors.*;
import org.toxicgames.memology.common.components.validation.ValidationError;
import org.toxicgames.memology.common.components.validation.ValidationErrorAdapter;
import org.toxicgames.memology.common.exceptions.ValidationException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidationErrorResponseProvider implements ErrorResponseProvider {

    private final ValidationErrorAdapter validationErrorAdapter;

    public ValidationErrorResponseProvider(ValidationErrorAdapter validationErrorAdapter) {
        this.validationErrorAdapter = validationErrorAdapter;
    }

    @Override
    public ErrorResponse getErrorResponse(Throwable throwable) {
        ValidationException e = (ValidationException) throwable;
        List<ValidationError> errors = e.getErrors().stream()
                .map(validationErrorAdapter::convert)
                .collect(Collectors.toList());
        return new ErrorResponse(ErrorCode.VALIDATION_ERROR, errors);
    }

    @Override
    public boolean supports(Throwable throwable) {
        return ValidationException.class.isAssignableFrom(throwable.getClass());
    }

}
