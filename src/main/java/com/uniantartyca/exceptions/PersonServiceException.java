package com.uniantartyca.exceptions;

public class PersonServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonServiceException() {
        super();
    }
    
    public PersonServiceException(String message) {
        super(message);
    }

    public PersonServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    
    public PersonServiceException(Throwable cause) {
        super(cause);
    }

    protected PersonServiceException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
	
}
