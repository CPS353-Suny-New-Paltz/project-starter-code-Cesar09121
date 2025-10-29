import project.processapi.DataStorageAPI;
import project.processapi.DataReadRequest;
import project.processapi.DataReadResponse;
import project.processapi.DataWriteRequest;
import project.processapi.DataWriteResponse;
import project.processapi.DataOperationStatus;

import java.util.ArrayList;
import java.util.List;

/*
 * Test data storage implementation that works with the in-memory data
 * Used for integration testing instead of actual file/database operation	
 */ 

public class TestDataStorageMemory implements DataStorageAPI{
	
	// Reads input from the storage
	@Override
	public DataReadResponse readInput(DataReadRequest request) {
		// Checks if this is a request for in-memory test input
		if(request.getLocation().equals("in_memory_input")) {
			// Creates test input with specific value (1,10,25)	
			List<Integer>testData = new ArrayList<>();
			testData.add(1);
			testData.add(10);
			testData.add(25); 
			
			// Returns test data with success status
		    return new DataReadResponse(testData,DataOperationStatus.SUCCESS); 
		}
		// Failed if the location doesn't match
		return new DataReadResponse(null,DataOperationStatus.FAILED); 
	}
	
	
	// Writes output data to the storage
	 public DataWriteResponse writeOutput(DataWriteRequest request) {
		// Checks if this is a request for in-memory test output
		 if(request.getLocation().equals("in_memory_output")) {
			 return new DataWriteResponse(DataOperationStatus.SUCCESS);
		 }
		 // Failed if the location doesn't match
		 return new DataWriteResponse(DataOperationStatus.FAILED);
	 }

}
