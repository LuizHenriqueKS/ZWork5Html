package br.zul.zwork5.html.exception;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlNodeStringifyException extends ZHtmlException {

    public ZHtmlNodeStringifyException() {
    }

    public ZHtmlNodeStringifyException(String message, Object... args) {
        super(message, args);
    }

    public ZHtmlNodeStringifyException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }

    public ZHtmlNodeStringifyException(Throwable cause) {
        super(cause);
    }

    public ZHtmlNodeStringifyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
