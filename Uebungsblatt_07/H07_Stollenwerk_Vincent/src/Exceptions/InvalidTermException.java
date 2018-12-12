package Exceptions;

public class InvalidTermException extends Exception {
	
	private String term;
	private String message;
	
	/**
	 * @param term
	 * @param message
	 */
	public InvalidTermException(String term, String message) {
		this.term = term;
		this.message = message;
	}
	
	/**
	 * @return term
	 */
	public String getTerm() {
		return term;
	}
	
	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
}
