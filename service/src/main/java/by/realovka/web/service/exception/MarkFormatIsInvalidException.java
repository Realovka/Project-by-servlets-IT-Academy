package by.realovka.web.service.exception;

public class MarkFormatIsInvalidException extends RuntimeException {

    public MarkFormatIsInvalidException() {
        super();
    }

    public MarkFormatIsInvalidException(String message) {
        super(message);
    }

    public MarkFormatIsInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public MarkFormatIsInvalidException(Throwable cause) {
        super(cause);
    }

    protected MarkFormatIsInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
