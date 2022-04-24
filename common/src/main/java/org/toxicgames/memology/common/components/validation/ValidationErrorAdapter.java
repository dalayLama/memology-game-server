package org.toxicgames.memology.common.components.validation;

import org.springframework.validation.ObjectError;
import org.toxicgames.memology.common.components.validation.ValidationError;

public interface ValidationErrorAdapter {

    ValidationError convert(ObjectError objectError);

}
