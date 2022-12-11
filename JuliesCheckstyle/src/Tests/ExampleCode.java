package Tests;

import java.util.HashSet;
public class ExampleCode {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		int five;
		five = 5;
		five = 6;
		int six;
		six = 6; 
		boolean flag = (five < six) && (2 < 3); 
		String.valueOf(x);
		flags.someMethod();
		OtherMethod();
		five++;
		switch (five) {
			case 0:
				break;
			case 6:
				break;
			default:
				break;
		}
		
	}
	public static void OtherMethod() {
		
	}

}
//Halstead length = operators + operands = 23
//Halstead vocab = unique +unique = 13
//Halstead difficulty = uniqueOperators * (Operands/(2*UniqueOperands)) = 3.333
//Halstead volume = length* log2(vocab) = 23 * ln(13) = 58.9938
//Halstead effort = diff * vol = 196.64
//Cyclomatic complexity = 3
//lines of code = 34 (line no of last right curly)
//internal method calls = 1
//external method calls = 2
//maintainability = 171 - 5.2*ln(volume)-0.23*cyclomaticComp-16.2*ln(linesofcode)+50*sin(sqrt(2.4*percentComments))
//  = 171-21.2026 -.69 - 56.1449 + 44.4566 = 137.419
