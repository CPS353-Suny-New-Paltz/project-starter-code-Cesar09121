package project.conceptualapi;

/*
 * Request for a single calculation (computation) operation
 * Contains the input number for factorial calculation
 */
public class ComputationRequest {

	private final int inputValue;
	
	// Creates a request with the number for factorial calculation (should be positive number)
	public ComputationRequest(int inputValue) {
		this.inputValue = inputValue;
	}
	
	// Gets the number that is stored in the request
	public int getInputValue() {
		return inputValue;
	}
}
