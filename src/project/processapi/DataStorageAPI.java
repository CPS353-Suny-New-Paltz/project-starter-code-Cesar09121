package project.processapi;
import project.annotations.ProcessAPI;

/*
 * Main API interface between the data storage (database) system and compute engine
 * Data storage system handles where the reading from and writing to user specified source
 * Separates database operation from computation logic
 */
@ProcessAPI
public interface DataStorageAPI {

	// Reads integer from the specified input source location
	DataReadResponse readInput(DataReadRequest readRequest);
	
	// Write formatted output/result to the specified output destination 
	DataWriteResponse writeOutput(DataWriteRequest writeRequest);
	
}
