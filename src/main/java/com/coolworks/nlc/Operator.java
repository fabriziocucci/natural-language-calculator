package com.coolworks.nlc;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

enum Operator {
	
	ADD("+", 1, "add", "plus") {
		@Override
		public double apply(double operand1, double operand2) {
			return operand1 + operand2;
		}
	}, 
	
	SUBTRACT("-", 1, "subtract", "minus", "less") {
		@Override
		public double apply(double operand1, double operand2) {
			return operand1 - operand2;
		}
	}, 
	
	MULTIPLY("*", 2, "multiply-by", "times") {
		@Override
		public double apply(double operand1, double operand2) {
			return operand1 * operand2;
		}
	}, 
	
	DIVIDE("/", 2, "divide-by", "over") {
		@Override
		public double apply(double operand1, double operand2) {
			return operand1 / operand2;
		}
	};
	
	private static final Map<String, Operator> aliasToOperator = new HashMap<>(); 
	static {
		Arrays.stream(Operator.values()).forEach(operator -> {
			operator.aliases.stream().forEach(operatorAlias -> {
				aliasToOperator.put(operatorAlias, operator);
			});
		});
	}
	
	private final String symbol;
	private final int precedence;
	private final Set<String> aliases;
	
	private Operator(String symbol, int precedence, String... aliases) {
		this.symbol = symbol;
		this.precedence = precedence;
		this.aliases = new HashSet<String>();
		this.aliases.add(symbol);
		Collections.addAll(this.aliases, aliases);
	}
	
	public String getSymbol() {
		return symbol;
	}

	public abstract double apply(double operand1, double operand2);
	
	public static Operator fromAlias(String operatorAlias) {
		Operator operator = aliasToOperator.get(operatorAlias);
		if (operator != null) {
			return operator;
		} else {
			throw new IllegalArgumentException("Unable to find operator from alias" + operatorAlias);
		}
	}
	
	public static boolean isOperator(String operatorAlias) {
		return aliasToOperator.containsKey(operatorAlias);
	}

	public boolean hasLessPriorityThan(Operator that) {
		return this.precedence < that.precedence;
	}
	
}