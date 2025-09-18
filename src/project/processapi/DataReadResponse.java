package project.processapi;

import java.util.List;

/*
 * Response contains data that's read from the storage
 * Includes the actual data and the status if the operation worked within a message
 */
public class DataReadResponse {

	
	private final List<Integer> data;
	private final boolean success;
	private final String message;
	
	// Creates a new response with read data and operation status
	public DataReadResponse(List<Integer>data,boolean success, String message) {
		this.data=data;
		this.success=success;
		this.message=message;
	}

	// Gets the data that's read successfully from the storage
	// Should be a list of integers if success or null if failed
	public List<Integer> getData() {
		return data;
	}

	// Gets the status to see if the operation worked
	public boolean isSuccess() {
		return success;
	}
	
	// Explains what happened if the operation failed and giving a congratulation message if successful
	public String getMessage() {
		return message;
	}
	
	
	
}
