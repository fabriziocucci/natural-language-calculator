package com.coolworks.nlc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class NaturalLanguageCalculator {
	
	/**
	 * It performs simple natural language calculations by:
	 * <ol>
	 * 	<li> converting the human readable calculation into its corresponding infix representation; </li>
	 * 	<li> converting the infix representation of the calculation into its corresponding postfix notation; </li>
	 * 	<li> evaluating the postfix representation of the calculation and producing the numeric result. </li>
	 * </ol>
	 * 
	 * @param calculation is the input calculation expressed as human readable string
	 * @return the numeric evaluation of the input calculation expressed as human readable string
	 */
	public static double performCalculation(String calculation) {
		List<String> infixRepresentation = getInfixRepresentationFromString(calculation);
		List<String> postfixRepresentation = getPostfixRepresentationFromInfixRepresentation(infixRepresentation);
		return evaluatePostfixRepresentation(postfixRepresentation);
	}
	
	/**
	 * Utility used to convert any simple human readable calculation into its corresponding infix representation.
	 * 
	 * @param calculation is the input calculation expressed as human readable string
	 * @return the infix representation of the input calculation expressed as human readable string
	 */
	private static List<String> getInfixRepresentationFromString(String calculation) {
		return Arrays.stream(calculation.split("\\s+")).map(token -> {
			if (Operator.isOperator(token)) {
				return Operator.fromAlias(token).toString();
			} else if (Number.isNumber(token)) {
				return Number.fromName(token).toString();
			} else {
				throw new IllegalArgumentException("Unable to parse token " + token);
			}
		}).collect(Collectors.toList());
	}

	/**
	 * Simplified implementation of the <b>shunting-yard</b> algorithm used for parsing mathematical expressions specified in infix notation.
	 * Here it is used to produce the postfix representation (Reverse Polish Notation) of the input calculation expressed as infix notation.
	 * 
	 * @param infixRepresentation is the input calculation expressed as infix notation
	 * @return the postfix representation of the input calculation expressed as infix notation
	 */
	private static List<String> getPostfixRepresentationFromInfixRepresentation(List<String> infixRepresentation) {
		
        List<String> output = new ArrayList<>();
        Deque<String> stack  = new LinkedList<>();

        for (String token : infixRepresentation) {
        	
            if (Operator.isOperator(token)) {
            	Operator operator = Operator.fromAlias(token);
            	while (!stack.isEmpty() && operator.hasLessPriorityThan(Operator.fromAlias(stack.peek()))) {
                	output.add(stack.pop());
                }
                stack.push(token);
            } else {
                output.add(token);
            }
            
        }

        while (!stack.isEmpty()) {
        	output.add(stack.pop());
        }

        return output;
    }
	
	/**
	 * Classic implementation of the algorithm for evaluating any postfix expression
	 * 
	 * @param postfixRepresentation is the input calculation expressed as postfix notation
	 * @return the numeric evaluation of the input calculation expressed as postfix notation
	 */
	private static double evaluatePostfixRepresentation(List<String> postfixRepresentation) {
		
		Deque<String> stack  = new LinkedList<>();
         
	    for (String token : postfixRepresentation) {
	    	
	        if (Operator.isOperator(token)) {
	        	Operator operator = Operator.fromAlias(token);
	        	double operand2 = Double.parseDouble(stack.pop());  
	            double operand1 = Double.parseDouble(stack.pop());      
	            double result = operator.apply(operand1, operand2);
	            stack.push(String.valueOf(result));              
	        } else {
	        	stack.push(token);
	        }
	        
	    }          
	          
	    return Double.parseDouble(stack.pop());
	}
	
}
