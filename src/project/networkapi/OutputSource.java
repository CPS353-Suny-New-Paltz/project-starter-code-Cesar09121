package project.networkapi;

/**
 * Interface for output destination
 * Returns location identifier like input source (URL, file path, ...)
 * 
 */
public interface OutputSource {
	
	String getLocation();
}
