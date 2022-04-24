package org.toxicgames.memology.common.components.errors.impls.providers;

import org.springframework.stereotype.Component;
import org.toxicgames.memology.common.components.errors.ErrorResponse;
import org.toxicgames.memology.common.components.errors.ErrorResponseData;
import org.toxicgames.memology.common.components.errors.ErrorResponseProvider;

@Component
public class ErrorResponseDataErrorResponseProvider implements ErrorResponseProvider {

    @Override
    public ErrorResponse getErrorResponse(Throwable throwable) {
        ErrorResponseData data = (ErrorResponseData) throwable;
        return new ErrorResponse(data.getErrorCode(), data.getPayload());
    }

    @Override
    public boolean supports(Throwable throwable) {
        return ErrorResponseData.class.isAssignableFrom(throwable.getClass());
    }

}
