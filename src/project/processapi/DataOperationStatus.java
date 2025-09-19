package project.processapi;

/*
 * Status enum for data storage operations
 * Contains success or failure checking for read and write operations
 */

public enum DataOperationStatus {
SUCCESS(true, "Data operation completed successfully!!!"),
FAILED(false, "Data operation failed!!!");
	
	private final boolean success;
	private final String message;
	
	// Creates a status with success check and message
	private DataOperationStatus(boolean success, String message) {
        this.success = success; 	// Returns true if succeed or false if failed
        this.message = message;     // Return a message of the result/output
    }

	// Checks if the operation's successful
	public boolean isSuccess() {
		return success;
	}

	// Gets the message of the status
	public String getMessage() {
		return message;
	}
	
	
}

