package project.networkAPI;


/**
 * Enum for tracking job status instead of using raw booleans
 * Combines both status checking and user messages in one place
 */

public enum ComputingJobSuccess {
	SUCCESS(true, "Computing job submitted successfully!!!"),
	FAILED(false, "Computing job failed to submit");
	
	private final boolean success;
	private final String message;
	
	
	// Constructor that sets success status and message
	private ComputingJobSuccess(boolean success, String message) {
		this.success = success; //success whether this represents a successful operation
		this.message = message; //message user-friendly description of this status
	     
	}
	
	// Returns true if this status represents a successful operation
	public boolean success() {
		return success;
	}
	
	// Gets the user-friendly message for status
	public String getMessage() {
		return message;
	}
	

}
 