package com.threepie.stocknewsticker.exception;

/**
 * Exception thrown upon failure to authenticate to the NewsAPI. Due to incorrect API Key.
 */
public class AuthFailureException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @param message The message to attach to this Exception upon throwing.
     */
    public AuthFailureException(String message) {
        super(message);
    }
}