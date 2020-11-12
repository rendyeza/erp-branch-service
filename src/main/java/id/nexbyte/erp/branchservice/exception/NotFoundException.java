package id.nexbyte.erp.branchservice.exception;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = -4216910614468760516L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String s) {
        super(s);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
