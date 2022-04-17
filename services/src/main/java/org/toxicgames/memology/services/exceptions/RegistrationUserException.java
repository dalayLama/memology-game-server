package org.toxicgames.memology.services.exceptions;

public class RegistrationUserException extends RuntimeException {

    public RegistrationUserException() {
    }

    public RegistrationUserException(String message) {
        super(message);
    }

    public RegistrationUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationUserException(Throwable cause) {
        super(cause);
    }

    public RegistrationUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
