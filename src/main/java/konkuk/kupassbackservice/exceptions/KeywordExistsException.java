package konkuk.kupassbackservice.exceptions;

public class KeywordExistsException extends IllegalArgumentException {
    public KeywordExistsException() {
    }

    public KeywordExistsException(String s) {
        super(s);
    }

    public KeywordExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeywordExistsException(Throwable cause) {
        super(cause);
    }
}
