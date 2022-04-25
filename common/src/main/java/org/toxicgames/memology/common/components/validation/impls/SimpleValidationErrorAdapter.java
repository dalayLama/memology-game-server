package org.toxicgames.memology.common.components.validation.impls;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.toxicgames.memology.common.components.AppMessageSource;
import org.toxicgames.memology.common.components.validation.ValidationErrorAdapter;
import org.toxicgames.memology.common.components.validation.ValidationError;

@Component
public class SimpleValidationErrorAdapter implements ValidationErrorAdapter {

    private final AppMessageSource messageSource;

    public SimpleValidationErrorAdapter(AppMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public ValidationError convert(ObjectError objectError) {
        String objectName = objectError.getObjectName();
        String fieldName = null;
        if (objectError instanceof FieldError) {
            fieldName = ((FieldError) objectError).getField();
        }
        String message = messageSource.getMessage(objectError);
        return new ValidationError(objectName, fieldName, message);
    }

}
