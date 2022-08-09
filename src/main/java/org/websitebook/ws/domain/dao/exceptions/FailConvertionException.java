package org.websitebook.ws.domain.dao.exceptions;

public class FailConvertionException extends Exception {
	
	private static final String MESSAGE = "Error was produced in the convertion";
    
    public FailConvertionException() {
        super(MESSAGE);
    }

    public FailConvertionException(String message) {
        super(message);
    }	
    
    public FailConvertionException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public FailConvertionException(String message, Throwable cause) {
        super(message, cause);
    }
}
	