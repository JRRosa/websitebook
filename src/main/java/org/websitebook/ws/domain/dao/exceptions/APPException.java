package org.websitebook.ws.domain.dao.exceptions;

public class APPException extends Exception {

    private static final String MESSAGE = "Error was produced by APP";
    
    public APPException() {
        super(MESSAGE);
    }

    public APPException(String message) {
        super(message);
    }
    
    public APPException(String message, Throwable cause) {
        super(message, cause);
    }
}
