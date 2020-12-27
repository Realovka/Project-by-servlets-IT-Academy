package by.realovka.web.service.exception;

public class NoSuchTrainerException extends RuntimeException {
    public NoSuchTrainerException() {
        super();
    }

    public NoSuchTrainerException(String message) {
        super(message);
    }

    public NoSuchTrainerException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchTrainerException(Throwable cause) {
        super(cause);
    }

    protected NoSuchTrainerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
