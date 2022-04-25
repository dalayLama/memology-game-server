package org.toxicgames.memology.common.components.errors.impls.providers;

import org.springframework.stereotype.Component;
import org.toxicgames.memology.common.components.errors.ErrorCode;
import org.toxicgames.memology.common.components.errors.ErrorResponse;
import org.toxicgames.memology.common.components.errors.ErrorResponseProvider;

import javax.persistence.EntityNotFoundException;

@Component
public class EntityNotFoundErrorResponseProvider implements ErrorResponseProvider {

    @Override
    public ErrorResponse getErrorResponse(Throwable throwable) {
        EntityNotFoundException e = (EntityNotFoundException) throwable;
        return new ErrorResponse(ErrorCode.ENTITY_NOT_FOUND, e.getMessage());
    }

    @Override
    public boolean supports(Throwable throwable) {
        return EntityNotFoundException.class.isAssignableFrom(throwable.getClass());
    }

}
