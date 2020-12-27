package by.realovka.web.service.exception;

public class SuchTrainerIsAlreadyExistException extends RuntimeException {
    public SuchTrainerIsAlreadyExistException() {
        super();
    }

    public SuchTrainerIsAlreadyExistException(String message) {
        super(message);
    }

    public SuchTrainerIsAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchTrainerIsAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected SuchTrainerIsAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
