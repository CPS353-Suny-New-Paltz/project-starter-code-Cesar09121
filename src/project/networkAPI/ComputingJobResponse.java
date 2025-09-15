package project.networkAPI;

/**
 * Response sent back to user after submitting a computing job
 */

public interface ComputingJobResponse {

	/**
	 * Gets the input source used in the job.
	 * @return input source
	 */
	Input getInput();
	
	/**
	 * Returns location identifier for input source.
	 * @return location string (URL, file path, etc.)
	 */
	Output getOutput();
	
	/**
	 * Creates method for returning default delimiters.
	 * @return default delimiters with " ; " and "! = "
	 */
	Delimiters getDelimiters();
	
	/**
	 * Submits a computing job for processing.
	 * @param computingJobRequest contains input, output, and delimiter details
	 * @return response with job status and information
	 */
	ComputingJobSuccess getStatus();
	
}
