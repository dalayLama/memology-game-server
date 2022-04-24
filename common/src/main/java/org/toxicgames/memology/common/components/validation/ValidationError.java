package org.toxicgames.memology.common.components.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationError {

    private final String object;

    private final String property;

    private final String message;

}
