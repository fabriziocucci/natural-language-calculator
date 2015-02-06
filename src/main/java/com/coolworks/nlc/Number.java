package com.coolworks.nlc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

enum Number {
	
	ZERO("zero", 0), ONE("one", 1), TWO("two", 2), THREE("three", 3), FOUR("four", 4), FIVE("five", 5), SIX("six", 6), SEVEN("seven", 7), EIGHT("eight", 8), NINE("nine", 9);
	
	/**
	 * Dictionary used to retrieve a specific {@link Number} enum constant given its human readable string.
	 */
	private static final Map<String, Number> nameToNumber = new HashMap<>();
	static {
		Arrays.stream(Number.values()).forEach(number -> {
			nameToNumber.put(number.name, number);
		});
	}
	
	/**
	 * Number name as human readable string.
	 */
	private final String name;
	
	/**
	 * Number value as integer.
	 */
	private final int value;

	private Number(String name, int value) {
		this.name = name;
		this.value = value;
	}		
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}

	/**
	 * Static facility used to retrieve a specific {@link Number} enum constant given its human readable string.
	 * 
	 * @param numberName is the human readable string of the number
	 * @return the enum constant identified by its human readable string
	 */
	public static Number fromName(String numberName) {
		return Optional.ofNullable(nameToNumber.get(numberName)).orElseThrow(() -> new IllegalArgumentException("Unable to find number from name" + numberName));
	}
	
	/**
	 * Static facility used to verify that a particular token is actually a number.
	 * 
	 * @param token is the token to check for validity
	 * @return whether the input token is a number
	 */
	public static boolean isNumber(String token) {
		return nameToNumber.containsKey(token);
	}
	
}