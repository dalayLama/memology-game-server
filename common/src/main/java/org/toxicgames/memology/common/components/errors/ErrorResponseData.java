package org.toxicgames.memology.common.components.errors;

public interface ErrorResponseData {

    ErrorCode getErrorCode();

    default Object getPayload() {
        return null;
    }

}
