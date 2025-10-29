package project.networkapi;
/*
 * Input source that reads from a file on disk
 * Used for file-based input in the computing engine
 */
public class FileInputSource implements InputSource {
	private final String location;
	
	// Creates a file input source with the specified file path
	public FileInputSource(String location) {
		this.location=location;
	}
	
	@Override
	// Gets the file path for this input source
	public String getLocation() {
		return location;
	}

}
