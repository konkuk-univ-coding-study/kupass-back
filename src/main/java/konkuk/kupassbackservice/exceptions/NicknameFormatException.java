package konkuk.kupassbackservice.exceptions;

public class NicknameFormatException extends IllegalArgumentException {
    public NicknameFormatException() {
    }

    public NicknameFormatException(String s) {
        super(s);
    }

    public NicknameFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public NicknameFormatException(Throwable cause) {
        super(cause);
    }
}
