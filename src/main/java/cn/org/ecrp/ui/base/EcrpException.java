package cn.org.ecrp.ui.base;

public class EcrpException extends RuntimeException {

    public EcrpException() {
    }

    public EcrpException(String message) {
        super(message);
    }

    public EcrpException(String message, Throwable cause) {
        super(message, cause);
    }

    public EcrpException(Throwable cause) {
        super(cause);
    }
}
