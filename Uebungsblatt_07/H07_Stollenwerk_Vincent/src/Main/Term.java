package Main;

import Exceptions.InvalidTermException;

public class Term {
	
	private String term;
	private String result;
	
	/**
	 * 
	 * @param term
	 * @throws InvalidTermException
	 */
	public Term(String term) throws InvalidTermException {
		
		// Remove whitespaces
		term.replaceAll("\\s", "");
		
		// Check for empty string
		if (term.isEmpty()) {
			throw new InvalidTermException(term, "Term is empty");
		}
		
		// Check for correct braces
		if (!checkBraces(term)) {
			throw new InvalidTermException(term, "Wrong number of closing braces");
		}
		
		// Only valid characters check
		if (!term.matches("[\\d().+/*-]*")) {
			throw new InvalidTermException(term, "Contains invalid character");
		}
		
		// Check String is a correct term
		// RegEx "ausgeschrieben":
		
		// Oeffnende Klammern (beliebig viele)
		// Optional ein Minus Zeichen
		// Mindestens eine Ziffer
		// Optional ein Punkt mit mindestens einer anschließenden Ziffer
		
		// Gruppe 1
			// Ein Operator (+ - * /)
			// Oeffnende Klammern (beliebig viele)
			// Optional ein Minus Zeichen
			// Mindestens eine Ziffer
			// Optional ein Punkt mit mindestens einer anschließenden Ziffer
			// Beliebig viele schließende Klammern
		// Gruppe 1 beliebeig oft (auch 0 mal)
		
		// Beliebig viele schließende Klammern
		if (term.matches("\\(*-?\\d+(\\.\\d+)?([\\+\\-\\*\\/]\\(*-?\\d+(\\.\\d+)?\\)*)*\\)*")) {
			this.term = term;
		}
		
		// If term has correct brace usage and only valid characters
		// There has to be a problem regarding the operator usage
		throw new InvalidTermException(term, "Incorrect operator usage");
	}
	
	/**
	 * @param term
	 * @return only true when every closing brace can be matched to an opening brace
	 */
	private static boolean checkBraces(String term) {
		
		int openedBraces = 0;
		
		for (char c : term.toCharArray()) {
			
			switch (c) {
				case '(':
					openedBraces++;
					break;
				case ')':
					openedBraces--;
			}
			
			if (openedBraces < 0) {
				return false;
			}
		}
		
		return openedBraces == 0;
	}
}
