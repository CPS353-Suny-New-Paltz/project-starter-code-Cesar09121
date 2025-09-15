package project.networkAPI;

/**
 * Interface for input source
 * Returns location string (URL file path, ...)
 */
public interface Input {
	/**
     * Returns location identifier for input source.
     * @return location string (URL, file path, etc.)
     */
	String getLocation();
	
}
