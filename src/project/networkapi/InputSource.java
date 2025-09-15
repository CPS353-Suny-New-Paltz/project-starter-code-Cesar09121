package project.networkapi;

/**
 * Interface for input source
 * Returns location string (URL file path, ...)
 */
public interface InputSource {
	/**
     * Returns location identifier for input source.
     * 
     * @return location string (URL, file path, etc.)
     */
	String getLocation();
	
}
