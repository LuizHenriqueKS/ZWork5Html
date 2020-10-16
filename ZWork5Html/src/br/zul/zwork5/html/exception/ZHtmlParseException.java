package br.zul.zwork5.html.exception;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlParseException extends ZHtmlException {

    public ZHtmlParseException() {
        
    }

    public ZHtmlParseException(String message, Object... args) {
        super(message, args);
    }

    public ZHtmlParseException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }

    public ZHtmlParseException(Throwable cause) {
        super(cause);
    }

    public ZHtmlParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
