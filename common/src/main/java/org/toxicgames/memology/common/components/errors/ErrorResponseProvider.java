package org.toxicgames.memology.common.components.errors;

public interface ErrorResponseProvider {

    ErrorResponse getErrorResponse(Throwable throwable);

    boolean supports(Throwable throwable);

}
