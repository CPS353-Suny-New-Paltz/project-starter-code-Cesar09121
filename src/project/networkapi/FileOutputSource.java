package project.networkapi;
/*
 * Output destination that writes to a file on disk
 * Used for file-based output in the computing engine
 */
public class FileOutputSource implements OutputSource {
	
	private final String location;
	
	// Creates a file output source with the specified file path
	public FileOutputSource(String location) {
		this.location =location;
	}
	
	@Override
	// Gets the file path for this output destination
	public String getLocation() {
		return location;
	}

}
