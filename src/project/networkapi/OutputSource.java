package project.networkapi;

/**
 * Interface for output destination
 * Returns location identifier like input source (URL, file path, ...)
 * 
 */
public interface OutputSource {
	/**
     * Returns location identifier for output destination.
     * 
     *@return location string (URL, file path, etc.)
     */
	String getLocation();
}
