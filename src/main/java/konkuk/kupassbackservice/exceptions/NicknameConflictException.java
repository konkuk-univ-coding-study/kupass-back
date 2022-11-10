package konkuk.kupassbackservice.exceptions;

public class NicknameConflictException extends IllegalArgumentException {
    public NicknameConflictException() {
    }

    public NicknameConflictException(String s) {
        super(s);
    }

    public NicknameConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public NicknameConflictException(Throwable cause) {
        super(cause);
    }
}
