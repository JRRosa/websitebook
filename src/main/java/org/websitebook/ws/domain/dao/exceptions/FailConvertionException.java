package org.websitebook.ws.domain.dao.exceptions;

public class FailConvertionException extends Exception {
	
	private static final String MESSAGE = "Error was produced in the convertion";
    
    public FailConvertionException() {
        super(MESSAGE);
    }
    
    public FailConvertionException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
	