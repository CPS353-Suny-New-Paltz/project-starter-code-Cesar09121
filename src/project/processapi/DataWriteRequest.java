package project.processapi;

public class DataWriteRequest {
	
	private final String location;
	private final String formattedResult;
	
	public DataWriteRequest(String location, String formattedResult) {
		this.location=location;
		this.formattedResult=formattedResult;
	}

	public String getLocation() {
		return location;
	}

	public String getFormattedResult() {
		return formattedResult;
	}
	
}
