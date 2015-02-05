package com.coolworks.nlc;

import static org.junit.Assert.*;
import org.junit.Test;

public class NaturalLanguageCalculatorTest {

	private static final double DELTA = 0.01;
	
	@Test
	public void testPerformCalculation() {
		
		String calculation1 = "nine over eight plus four times two divide-by three";
		double actualResult1 = NaturalLanguageCalculator.performCalculation(calculation1);
		double expectedResult1 = 3.79; 
		assertEquals(expectedResult1, actualResult1, DELTA);
		
		String calculation2 = "one plus two";
		double actualResult2 = NaturalLanguageCalculator.performCalculation(calculation2);
		double expectedResult2 = 3.0; 
		assertEquals(expectedResult2, actualResult2, DELTA);
		
		String calculation3 = "one plus two times three";
		double actualResult3 = NaturalLanguageCalculator.performCalculation(calculation3);
		double expectedResult3 = 7.0; 
		assertEquals(expectedResult3, actualResult3, DELTA);
		
		String calculation4 = "nine minus three times seven";
		double actualResult4 = NaturalLanguageCalculator.performCalculation(calculation4);
		double expectedResult4 = -12.0; 
		assertEquals(expectedResult4, actualResult4, DELTA);
		
		String calculation5 = "four minus eight plus six times nine";
		double actualResult5 = NaturalLanguageCalculator.performCalculation(calculation5);
		double expectedResult5 = 50.0; 
		assertEquals(expectedResult5, actualResult5, DELTA);

		String calculation6 = "seven over nine plus one";
		double actualResult6 = NaturalLanguageCalculator.performCalculation(calculation6);
		double expectedResult6 = 1.78; 
		assertEquals(expectedResult6, actualResult6, DELTA);
		
		String calculation7 = "two over four times two";
		double actualResult7 = NaturalLanguageCalculator.performCalculation(calculation7);
		double expectedResult7 = 1.0; 
		assertEquals(expectedResult7, actualResult7, DELTA);
		
	}

}
