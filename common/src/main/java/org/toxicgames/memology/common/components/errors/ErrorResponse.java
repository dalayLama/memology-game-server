package org.toxicgames.memology.common.components.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {

    private final ErrorCode errorCode;

    private final Object payload;

    public ErrorResponse(ErrorCode errorCode) {
        this(errorCode, null);
    }

}
