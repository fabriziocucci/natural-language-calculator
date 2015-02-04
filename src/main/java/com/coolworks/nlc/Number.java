package com.coolworks.nlc;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

enum Number {
	
	ZERO("zero", 0), ONE("one", 1), TWO("two", 2), THREE("three", 3), FOUR("four", 4), FIVE("five", 5), SIX("six", 6), SEVEN("seven", 7), EIGHT("eight", 8), NINE("nine", 9);
	
	private static final Map<String, Number> nameToNumber = Arrays.stream(Number.values()).collect(Collectors.toMap(number -> number.name, Function.<Number> identity()));
	
	private final String name;
	private final int value;

	private Number(String name, int value) {
		this.name = name;
		this.value = value;
	}		
	
	public String getSymbol() {
		return String.valueOf(value);
	}

	public static Number fromName(String numberName) {
		Number number = nameToNumber.get(numberName);
		if (number != null) {
			return number;
		} else {
			throw new IllegalArgumentException("Unable to find number from name" + numberName);
		}
	}
	
	public static boolean isNumber(String numberName) {
		return nameToNumber.containsKey(numberName);
	}
	
}