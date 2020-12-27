package by.realovka.web.service.exception;

public class ThemeFormatIsWrongException extends RuntimeException {
    public ThemeFormatIsWrongException() {
        super();
    }

    public ThemeFormatIsWrongException(String message) {
        super(message);
    }

    public ThemeFormatIsWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThemeFormatIsWrongException(Throwable cause) {
        super(cause);
    }

    protected ThemeFormatIsWrongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
