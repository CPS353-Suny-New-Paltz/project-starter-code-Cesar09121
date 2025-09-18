package project.conceptualapi;
import project.annotations.ConceptualAPI;

/*
 * Main API interface for factorial computation
 * The job handler uses this to ask the computation component to do math operation
 * Separates the math logic from file reading and job management logic
 */
@ConceptualAPI
public interface ComputationAPI {
	
	// Calculates factorial of the given number and return factorial result
	long computeFactorial(int input);
	
	// Formatted results for displaying with input integer, factorial result, and delimiters for result displaying
	String formatResult(int input, String inputOutputDelimiters, long output);
	
}
