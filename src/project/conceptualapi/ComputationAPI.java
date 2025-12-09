package project.conceptualapi;
import java.math.BigInteger;

import project.annotations.ConceptualAPI;

/*
 * Main API interface for factorial computation
 * The job handler uses this to ask the computation component to do math operation
 * Separates the math logic from file reading and job management logic
 */
@ConceptualAPI
public interface ComputationAPI {
	
	// Calculates factorial of the given number and return factorial result
	BigInteger computeFactorial(int input);
}
