package project.processapi;

/*
 * Response contains: 
 * - The status if the write operation is successful or failed
 * - Status information and explanations about what happened
 * 
 */
public class DataWriteResponse {
	
	private final DataOperationStatus status;
	
	// Create a new response with the write operation status
	public DataWriteResponse(DataOperationStatus status) {
		this.status = status;
	}

	// Gets the operation status (success or failed) and message
	public DataOperationStatus getStatus() {
		return status;
	}
		
	
	

}
