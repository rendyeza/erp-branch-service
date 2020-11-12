package id.nexbyte.erp.branchservice.exception;

public class AlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -8890102083440785862L;

    public AlreadyExistsException() {
        super();
    }

    public AlreadyExistsException(String s) {
        super(s);
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
