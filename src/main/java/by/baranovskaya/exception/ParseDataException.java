package by.baranovskaya.exception;

public class ParseDataException extends Exception{
    public ParseDataException() {}

    public ParseDataException(String message) {
        super(message);
    }

    public ParseDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseDataException(Throwable cause) {
        super(cause);
    }
}
