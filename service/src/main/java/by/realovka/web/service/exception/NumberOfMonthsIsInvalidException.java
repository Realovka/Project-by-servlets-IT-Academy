package by.realovka.web.service.exception;

public class NumberOfMonthsIsInvalidException extends RuntimeException {
    public NumberOfMonthsIsInvalidException() {
        super();
    }

    public NumberOfMonthsIsInvalidException(String message) {
        super(message);
    }

    public NumberOfMonthsIsInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberOfMonthsIsInvalidException(Throwable cause) {
        super(cause);
    }

    protected NumberOfMonthsIsInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
