package konkuk.kupassbackservice.exceptions;

public class PasswordFormatException extends IllegalArgumentException {
    public PasswordFormatException() {
    }

    public PasswordFormatException(String s) {
        super(s);
    }

    public PasswordFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordFormatException(Throwable cause) {
        super(cause);
    }
}
