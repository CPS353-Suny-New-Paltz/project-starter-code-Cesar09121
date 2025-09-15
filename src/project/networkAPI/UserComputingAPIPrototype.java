package project.networkAPI;
import project.annotations.NetworkAPIPrototype;


/**
 * Prototype implementation of the user computing API
 * This is a simple placeholder implementation for testing the API design
 * Real implementation would actually process the computing job
 */

public class UserComputingAPIPrototype implements UserComputingAPI {
	
	@Override
	@NetworkAPIPrototype
	
	public ComputingJobResponse submission (ComputingJobRequest computingJobRequest) {
		
		
		// Use default delimiters if none provided
		Delimiters delimiters = computingJobRequest.getDelimitersOrDefault();
		
		
		/*
		 * Create successful response using DefaultComputingJobResponse implementation
	     * Maintains wire compatibility if Input/Output implementation changes
		 * Returns original input/output objects
		 */
    		
		return new DefaultComputingJobResponse(
				computingJobRequest.getInput(),
				computingJobRequest.getOutput(),
				delimiters,
				ComputingJobSuccess.SUCCESS);
				
				
				
	}

}
