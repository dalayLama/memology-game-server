package org.toxicgames.memology.common.components.errors;

public interface ErrorResponseGenerator {

    ErrorResponse generate(Throwable throwable);

}
