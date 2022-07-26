package org.websitebook.ws.domain.dao.exceptions;

public class DBException extends Exception {
    
    private static final String MESSAGE = "Error was produced by DB";
    
    public DBException() {
        super(MESSAGE);
    }
    
    public DBException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
    

