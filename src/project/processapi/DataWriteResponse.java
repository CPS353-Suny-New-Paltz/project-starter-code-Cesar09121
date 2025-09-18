package project.processapi;

/*
 * Response contains: 
 * - The status if the write operation is successful or failed
 * - Status information and explanations about what happened
 * 
 */
public class DataWriteResponse {
	
	public final boolean success;
	public final String message;
	
	// Create a new response with the write operation status
	public DataWriteResponse(boolean success, String message) {
		this.message=message;
		this.success=success;
	}

	// Checks if the operation is successful or failed
	public boolean isSuccess() {
		return success;
	}

	// Gets information about what happened during the operation
	public String getMessage() {
		return message;
	}
	
	

}
