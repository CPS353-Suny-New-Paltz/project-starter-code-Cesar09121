package project.networkapi;
import project.processapi.DataStorageAPI;
/*
 * Empty implementation for UserComputingAPI for testing
 * Handles job submission from user but returns failure
 * Real logic will be added soon
 */
public class UserComputingAPIIm implements UserComputingAPI	{
	// Dependency on data storage component for reading/writing files
	private DataStorageAPI dataStorageAPI;
	
	// Constructor that takes data storage dependency
	public UserComputingAPIIm(DataStorageAPI dataStorageAPI) {
        this.dataStorageAPI = dataStorageAPI;
    }
	
	/*
	 * Placeholder implementation for job submission
	 */
	@Override
    public ComputingJobResponse submission(ComputingJobRequest request) {
        // Return failure response for now
        return new DefaultComputingJobResponse(
            request.getInput(), 
            request.getOutput(), 
            request.getDelimitersOrDefault(), 
            ComputingJobSuccess.FAILED
        );
    }
}
