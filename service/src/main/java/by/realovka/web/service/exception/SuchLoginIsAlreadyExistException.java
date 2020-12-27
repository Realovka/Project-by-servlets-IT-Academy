package by.realovka.web.service.exception;

public class SuchLoginIsAlreadyExistException extends RuntimeException {

    public SuchLoginIsAlreadyExistException() {
        super();
    }

    public SuchLoginIsAlreadyExistException(String message) {
        super(message);
    }

    public SuchLoginIsAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchLoginIsAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected SuchLoginIsAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
