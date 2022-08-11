package org.websitebook.ws.domain.service.exceptions;

public class APPException extends Exception {

    private static final String MESSAGE = "Error was produced by APP";
    
    public APPException() {
        super(MESSAGE);
    }
    
    public APPException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
