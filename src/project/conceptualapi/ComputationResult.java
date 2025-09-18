package project.conceptualapi;

/*
 * Holds the result of the factorial calculation 
 * Contains the input and calculated (computed) result
 * 
 */
public class ComputationResult {
	private final int input;
	private final long finalOutput;  // keeps type long because the type int cannot hold the result of number that is greater than 20
	private final boolean success;
	
	// Creates a result container with calculation data
	public ComputationResult(int input, long finalOutput, boolean success) {
		this.input=input;				// original number (input)
		this.finalOutput=finalOutput;   // final output after factorial calculation
		this.success=success;			// success if the calculation worked
	}

	// Gets the given input value for calculation
	public int getInput() {
		return input;
	}

	// Gets the output after computation 
	public long getFinalOutput() {
		return finalOutput;
	}
	
	// Return true if the computation worked successfully
	public boolean getSuccess() {
		return success;
	}

	
}
