package by.realovka.web.service.exception;

public class PasswordIsInvalidException extends RuntimeException {

    public PasswordIsInvalidException() {
        super();
    }

    public PasswordIsInvalidException(String message) {
        super(message);
    }

    public PasswordIsInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordIsInvalidException(Throwable cause) {
        super(cause);
    }

    protected PasswordIsInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
