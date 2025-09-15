package project.networkapi;


/**
 * Enum for tracking job status instead of using raw booleans.
 * Combines both status checking and user messages in one place
 */

public enum ComputingJobSuccess {
	SUCCESS(true, "Computing job submitted successfully!!!"),
	FAILED(false, "Computing job failed to submit");
	
	private final boolean success;
	private final String message;
	
	
	/**
	 * Constructor that sets success status and message.
	 * 
	 * @param success whether this represents successful operation
	 * @param message user-friendly description
	 */
	private ComputingJobSuccess(boolean success, String message) {
		this.success = success; //success whether this represents a successful operation
		this.message = message; //message user-friendly description of this status
	     
	}
	
	/**
	 * Returns true if this status represents successful operation.
	 * 
	 * @return true for success, false for error
	 */
	public boolean success() {
		return success;
	}
	
	/**
	 * Gets the user-friendly message for this status.
	 * 
	 * @return descriptive message string
	 */
	public String getMessage() {
		return message;
	}
	

}
 