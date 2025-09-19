package project.processapi;
import project.annotations.ProcessAPIPrototype;

/*
 * Demonstrates how the user uses the DataStorageAPI for reading and writing
 * 
 */


public class DataStorageAPIPrototype {
	@ProcessAPIPrototype
	// Shows the basics of using the API for reading input and writing output
	public void prototype(DataStorageAPI dataStorageAPI) {
		
		// Read input integers from input source
		DataReadResponse read = dataStorageAPI.readInput(new DataReadRequest(null));
		
		// Write formatted output for result destination
		DataWriteResponse write = dataStorageAPI.writeOutput(new DataWriteRequest(null,null));
	}
}
