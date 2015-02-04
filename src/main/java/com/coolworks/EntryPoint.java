package com.coolworks;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.coolworks.nlc.NaturalLanguageCalculator;

public class EntryPoint {
	
	public static void main(String[] args) {
		System.out.println("Please enter a calculation:");
		String calculation = readCalculationFromStandardInput();
		double result = NaturalLanguageCalculator.performCalculation(calculation);
		System.out.format("Result: %s", formatResult(result));
	}
	
	private static String readCalculationFromStandardInput() {
		try (Scanner scanner = new Scanner(System.in)) {
			return scanner.nextLine();
		}
	}
	
	private static String formatResult(double result) {
		return new DecimalFormat("0.##").format(result);
	}
	
}