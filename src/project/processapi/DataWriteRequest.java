package project.processapi;

/*
 * Request for writing computation output to the storage location
 * Contains the final destination and formatted result
 */
public class DataWriteRequest {
	
	private final String location;
	private final String formattedResult;
	
	// Creates a new request for writing result
	public DataWriteRequest(String location, String formattedResult) {
		this.location=location;
		this.formattedResult=formattedResult;
	}

	// Gets the output location for writing result
	public String getLocation() {
		return location;
	}

	// Returns computation result as a formatted string
	public String getFormattedResult() {
		return formattedResult;
	}
	
}
