package org.toxicgames.memology.common.components.errors.impls.providers;

import org.springframework.stereotype.Component;
import org.toxicgames.memology.common.components.errors.ErrorCode;
import org.toxicgames.memology.common.constants.BeanNames;
import org.toxicgames.memology.common.components.errors.ErrorResponseProvider;
import org.toxicgames.memology.common.components.errors.ErrorResponse;

@Component(BeanNames.DEFAULT_ERROR_RESPONSE_PROVIDER)
public class DefaultErrorResponseProvider implements ErrorResponseProvider {

    @Override
    public ErrorResponse getErrorResponse(Throwable throwable) {
        return new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @Override
    public boolean supports(Throwable throwable) {
        return true;
    }

}
