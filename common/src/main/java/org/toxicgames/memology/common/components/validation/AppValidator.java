package org.toxicgames.memology.common.components.validation;

import org.springframework.lang.NonNull;
import org.toxicgames.memology.common.exceptions.ValidationException;

public interface AppValidator {

    void validate(@NonNull Object object, String objectName) throws ValidationException;

}
