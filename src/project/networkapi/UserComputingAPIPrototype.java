package project.networkapi;
import project.annotations.NetworkAPIPrototype;


/**
 * Prototype implementation of the user computing API.
 * This is a simple placeholder implementation for testing the API design
 * Real implementation would actually process the computing job
 */

public class UserComputingAPIPrototype {
		
	@NetworkAPIPrototype
	
	public void prototype(UserComputingAPI userComputingAPI){
		
		// Call the submission method on the API
		// Submit computing job with input source, output destination, and delimiters
		
		ComputingJobResponse response = userComputingAPI.submission(new ComputingJobRequest(null, null, null));
		
		
					
	}

}
