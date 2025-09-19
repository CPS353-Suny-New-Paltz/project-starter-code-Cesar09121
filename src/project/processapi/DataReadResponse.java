package project.processapi;

import java.util.List;

/*
 * Response contains data that's read from the storage
 * Includes the actual data and the status if the operation worked within a message
 */
public class DataReadResponse {

	
	private final List<Integer> data;
	private final DataOperationStatus status;
	
	// Creates a new response with read data and operation status
	public DataReadResponse(List<Integer>data,DataOperationStatus status) {
		this.data=data;
		this.status = status;
	}

	// Gets the data that's read successfully from the storage
	// Should be a list of integers if success or null if failed
	public List<Integer> getData() {
		return data;
	}

	// Gets operation status and message
	public DataOperationStatus getStatus() {
		return status;
	}
	
	
	
}
