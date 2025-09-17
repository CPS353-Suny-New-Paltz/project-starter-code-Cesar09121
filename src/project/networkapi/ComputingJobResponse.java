package project.networkapi;

/**
 * Response sent back to user after submitting a computing job
 */

public interface ComputingJobResponse {

	// Gets input location in the computing job
	InputSource getInput();
	
	// Gets output destination in the computing job
	OutputSource getOutput();
	
	// Get the delimiters for formatting 
	Delimiters getDelimiters();
	
	// Gets job status within success checking and message
	ComputingJobSuccess getStatus();
	
}
