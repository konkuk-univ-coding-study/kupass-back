package konkuk.kupassbackservice.exceptions;

public class KeywordNotExistsException extends IllegalArgumentException {
    public KeywordNotExistsException() {
    }

    public KeywordNotExistsException(String s) {
        super(s);
    }

    public KeywordNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeywordNotExistsException(Throwable cause) {
        super(cause);
    }
}
