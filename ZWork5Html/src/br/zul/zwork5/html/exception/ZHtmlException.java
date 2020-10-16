package br.zul.zwork5.html.exception;

import br.zul.zwork5.exception.ZException;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlException extends ZException{

    public ZHtmlException() {
    }

    public ZHtmlException(String message, Object... args) {
        super(message, args);
    }

    public ZHtmlException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }

    public ZHtmlException(Throwable cause) {
        super(cause);
    }

    public ZHtmlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
