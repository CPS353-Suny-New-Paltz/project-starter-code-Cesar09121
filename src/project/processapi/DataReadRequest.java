package project.processapi;

/*
 * Request for reading input from the storage location
 * Contains the input location (where to read the data from)
 */
public class DataReadRequest {

	private final String location;
	
	// Creates a new request for reading data
	public DataReadRequest(String location) {
		this.location=location;
	}

	// Gets the data location to read the data
	public String getLocation() {
		return location;
	}
}
