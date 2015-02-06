package com.coolworks.nlc;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

enum Operator {
	
	ADD("+", 1, "add", "plus") {
		@Override
		public double apply(double operand1, double operand2) {
			return operand1 + operand2;
		}
	}, 
	
	SUBTRACT("-", 2, "subtract", "minus", "less") {
		@Override
		public double apply(double operand1, double operand2) {
			return operand1 - operand2;
		}
	}, 
	
	MULTIPLY("*", 3, "multiply-by", "times") {
		@Override
		public double apply(double operand1, double operand2) {
			return operand1 * operand2;
		}
	}, 
	
	DIVIDE("/", 4, "divide-by", "over") {
		@Override
		public double apply(double operand1, double operand2) {
			return operand1 / operand2;
		}
	};
	
	/**
	 * Dictionary used to retrieve a specific {@link Operator} enum constant given any of its natural language alias.
	 */
	private static final Map<String, Operator> aliasToOperator = new HashMap<>(); 
	static {
		Arrays.stream(Operator.values()).forEach(operator -> {
			operator.aliases.stream().forEach(alias -> {
				aliasToOperator.put(alias, operator);
			});
		});
	}
	
	/**
	 * Symbol representing the arithmetic operator (e.g. '+', '-', '*', '/').
	 */
	private final String symbol;
	
	/**
	 * Integer value representing the arithmetic operator precedence.
	 */
	private final int precedence;
	
	/**
	 * Set of permitted natural language aliases for the arithmetic operator.
	 */
	private final Set<String> aliases;
	
	private Operator(String symbol, int precedence, String... aliases) {
		this.symbol = symbol;
		this.precedence = precedence;
		this.aliases = new HashSet<String>();
		this.aliases.add(symbol);
		Collections.addAll(this.aliases, aliases);
	}
	
	@Override
	public String toString() {
		return symbol;
	}

	/**
	 * Simply compares this arithmetic operator precedence with another arithmetic operator.
	 * 
	 * @param that is the other arithmetic operator involved in the comparison
	 * @return whether this arithmetic operator has less priority than the other one
	 */
	public boolean hasLessPriorityThan(Operator that) {
		return this.precedence < that.precedence;
	}
	
	/**
	 * Each arithmetic operator implements this method accordingly.
	 * 
	 * @param operand1 is the first operand involved in the arithmetic operation
	 * @param operand2 is the second operand involved in the arithmetic operation
	 * @return the result of applying the arithmetic operator to the provided operands
	 */
	public abstract double apply(double operand1, double operand2);
	
	/**
	 * Static facility used to retrieve a specific {@link Operator} enum constant given any of its natural language alias.
	 * 
	 * @param operatorAlias is the natural language alias of the arithmetic operator
	 * @return the enum constant identified by its natural language alias
	 */
	public static Operator fromAlias(String operatorAlias) {
		return Optional.ofNullable(aliasToOperator.get(operatorAlias)).orElseThrow(() -> new IllegalArgumentException("Unable to find operator from alias" + operatorAlias));
	}
	
	/**
	 * Static facility used to verify that a particular token is actually an arithmetic operator.
	 * 
	 * @param token is the token to check for validity
	 * @return whether the input token is an arithmetic operator
	 */
	public static boolean isOperator(String token) {
		return aliasToOperator.containsKey(token);
	}

}