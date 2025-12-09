package project.conceptualapi;
import java.math.BigInteger;

import project.annotations.ConceptualAPIPrototype;

/*
 * Demonstrates how to use the ComputationAPI to do calculations
 * 
 */

public class ComputationAPIPrototype {
	
	// Using API for factorial calculations
	@ConceptualAPIPrototype
	public void prototype(ComputationAPI computationAPI) {
		
		// Calculating factorial of the given input value ( input value in this case is 4)
		BigInteger result = computationAPI.computeFactorial(4);
	
	}
}
