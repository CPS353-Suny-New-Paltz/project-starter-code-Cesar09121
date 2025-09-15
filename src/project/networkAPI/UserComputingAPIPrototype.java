package project.networkAPI;
import project.annotations.NetworkAPIPrototype;


/**
 * Prototype implementation of the user computing API
 * This is a simple placeholder implementation for testing the API design
 * Real implementation would actually process the computing job
 */

public class UserComputingAPIPrototype implements UserComputingAPI {
	/**
	 * Prototype implementation of job submission.
	 * Always returns SUCCESS for testing purposes.
	 * @param computingJobRequest contains input, output, and delimiter details
	 * @return response with job status and information
	 */
	
	@Override
	@NetworkAPIPrototype
	
	public ComputingJobResponse submission (ComputingJobRequest computingJobRequest) {
		
		
		// Use default delimiters if none provided
		Delimiters delimiters = computingJobRequest.getDelimitersOrDefault();
		
		
		// Create successful response using DefaultComputingJobResponse implementation
		// Maintains wire compatibility if Input/Output implementation changes
		// Returns original input/output objects
		return new DefaultComputingJobResponse(
				computingJobRequest.getInput(),
				computingJobRequest.getOutput(),
				delimiters,
				ComputingJobSuccess.SUCCESS);
				
				
				
	}

}
