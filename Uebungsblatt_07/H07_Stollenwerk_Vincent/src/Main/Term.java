package Main;

import java.util.regex.Pattern;

import Exceptions.InvalidTermException;
import Math.DoubleStringMath;

public class Term {
	
	private String term;
	private String result;
	
	/**
	 * @param term
	 * @throws InvalidTermException
	 */
	public Term(String term) throws InvalidTermException {
		
		// Remove whitespaces
		term = term.replaceAll("\\s", "");
		
		// Check for empty string
		if (term.matches("\\(*\\)*")) {
			throw new InvalidTermException(term, "Term is empty");
		}
		
		// Check for correct braces
		if (!checkBraces(term)) {
			throw new InvalidTermException(term, "Invalid brace use");
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
		} else {
			
			// If term has correct brace usage and only valid characters
			// There has to be a problem regarding the operator usage
			throw new InvalidTermException(term, "Incorrect operator usage");
		}
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
	
	/**
	 * If result isn't set, calculates result
	 * @return result
	 */
	public String getResult() {
		
		if (result == null) {
			evaluateTerm();
		}
		
		return result;
	}
	
	/**
	 * Calculates term and stores it in result
	 */
	private void evaluateTerm() {
		
		Utils.math = new DoubleStringMath();
		
		while (!isAtom()) {
			term = term.replaceAll("\\((-?\\d*(.\\d+)?)\\)", "$1");
			
			String innerMostExpression = findInnerMostExpression();
			String result = evaluateSimpleExpression(innerMostExpression);
			
			this.term = term.replaceFirst(Pattern.quote(innerMostExpression), result);
		}
		
		this.result = term;
		
	}
	
	/**
	 * @param term
	 * @return true if the term consists of a single number
	 */
	private boolean isAtom() {
		return term.matches("-?[\\d]*(.\\d+)?");
	}
	
	/**
	 * @return inner expression regarding braces
	 */
	private String findInnerMostExpression() {
		
		for (int i = 0; i < term.length(); i++) {
			
			if (term.charAt(i) == ')') {
				
				for (int j = i-1; j > -1; j--) {
					
					if (term.charAt(j) == '(') {
						
						String innerMostExpression = term.substring(j+1, i);
						return innerMostExpression;
					}
				}
			}
		}
		
		return term;
	}
	
	/**
	 * @param simple term containing only numbers and operators, no braces
	 * @return evaluation of term
	 */
	private static String evaluateSimpleExpression(String simple) {
		
		simple = removeMinusOperator(simple);
		
		for (int mulOrDivOperation = findMulOrDivOperation(simple); mulOrDivOperation != -1; mulOrDivOperation = findMulOrDivOperation(simple)) {
			
			simple = removeMinusOperator(simple);
			
			String simpleOperation = null;
			
			for (int i = mulOrDivOperation; i < simple.length(); i++) {
				
				if (simple.charAt(i) == '+') {
					
					simpleOperation = simple.substring(mulOrDivOperation, i);
					break;
					
				} else if (simple.charAt(i) == '*' || simple.charAt(i) == '/') {
					
					for (int j = i + 1; j < simple.length(); j++) {
						
						if (simple.charAt(j) == '+' || simple.charAt(j) == '/' || simple.charAt(j) == '*') {
							
							simpleOperation = simple.substring(mulOrDivOperation, j);
							break;
						}
					}
					
					break;
				}
			}
			
			if (simpleOperation == null) {
				simpleOperation = simple.substring(mulOrDivOperation);
			}
			
			if (simpleOperation.contains("*")) {
				
				String[] operators = simpleOperation.split(Pattern.quote("*"));
				
				String result = Utils.math.mul(operators[0], operators[1]);
				simple = simple.replaceFirst(Pattern.quote(simpleOperation), result);
			} else if (simpleOperation.contains("/")) {
				
				String[] operators = simpleOperation.split(Pattern.quote("/"));
				
				String result = Utils.math.div(operators[0], operators[1]);
				simple = simple.replaceFirst(Pattern.quote(simpleOperation), result);
			}
			
		}
		
		simple = removeMinusOperator(simple);
		
		for (int addOperation = findAddOperation(simple); addOperation != -1; addOperation = findAddOperation(simple)) {
			
			simple = removeMinusOperator(simple);
			
			String simpleOperation = null;
			
			for (int i = addOperation; i < simple.length(); i++) {
				
				if (simple.charAt(i) == '+') {
					
					for (int j = i + 1; j < simple.length(); j++) {
						
						if (simple.charAt(j) == '+') {
							
							simpleOperation = simple.substring(addOperation, j);
							break;
						}
						
					}
					break;

				}
			}
			
			if (simpleOperation == null) {
				simpleOperation = simple.substring(addOperation);
			}
			
			String[] operators = simpleOperation.split(Pattern.quote("+"));
			
			String result = Utils.math.add(operators[0], operators[1]);
			simple = simple.replaceFirst(Pattern.quote(simpleOperation), result);
		}
		
		return simple;
	}
	
	/**
	 * @param term
	 * @return term with minus operators replaced with plus negative number
	 */
	private static String removeMinusOperator(String term) {
		
		String resultTerm = term.replaceAll(Pattern.quote("--"), "+");
		
		resultTerm = resultTerm.replaceAll("([\\d)])-", "$1+-");
		
		return resultTerm;
	}
	
	/**
	 * Finds first multiplication or division in term
	 * @param term
	 * @return index of first operand. -1 if there isn't one
	 */
	private static int findMulOrDivOperation(String term) {
		
		int firstMul = findOperation(term, '*');
		int firstDiv = findOperation(term, '/');
		
		if (firstMul == -1) {
			return firstDiv;
		}
		
		if (firstDiv == -1) {
			return firstMul;
		}
		
		return Math.min(firstMul, firstDiv);
	}
	
	/**
	 * Finds first addition in term
	 * @param term
	 * @return index of first operand. -1 if there isn't one
	 */
	private static int findAddOperation(String term) {
		return findOperation(term, '+');
	}
	
	/**
	 * Finds first operation using operator
	 * @param term
	 * @param operator
	 * @return index of first operand. -1 if there is none
	 */
	private static int findOperation(String term, char operator) {
		
		char[] termCharArray = term.toCharArray();
		
		for (int i = 0; i < termCharArray.length; i++) {
			// Find operator
			if (termCharArray[i] == operator) {
				
				for (int j = i - 1; j > -1; j--) {
					if (termCharArray[j] == '/' || termCharArray[j] == '*' || termCharArray[j] == '+') {
						return j + 1;
					}
				}
				
				return 0;
			}			

		}
		
		return -1;
	}
}
