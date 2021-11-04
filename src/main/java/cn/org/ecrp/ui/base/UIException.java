package cn.org.ecrp.ui.base;

public class UIException extends RuntimeException {

    public UIException() {
    }

    public UIException(String message) {
        super(message);
    }

    public UIException(String message, Throwable cause) {
        super(message, cause);
    }

    public UIException(Throwable cause) {
        super(cause);
    }
}
