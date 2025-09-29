package project.processapi;
/*
 * Empty implementation of DataStorageAPI for testing
 * Provides placeholder methods for reading and writing data
 * Actual database operations will be added soon
 */
public class DataStorageAPIIm implements DataStorageAPI {
	
	/*
	 * Placeholder implementation for reading input
	 * Should return failure response
	 */
	@Override
    public DataReadResponse readInput(DataReadRequest request) {
        // Return empty failure response
        return new DataReadResponse(null, DataOperationStatus.FAILED);
    }
    
	/*
	 * Placeholder implementation for writing output
	 * Should return failure response
	 */
    @Override
    public DataWriteResponse writeOutput(DataWriteRequest request) {
        // Return failure response
        return new DataWriteResponse(DataOperationStatus.FAILED);
    }

}
