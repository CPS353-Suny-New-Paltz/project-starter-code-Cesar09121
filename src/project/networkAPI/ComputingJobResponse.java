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
     * Gets the output destination used in the job.
     * @return output destination
     */
	Output getOutput();
	
	/**
     * Gets the delimiters used for formatting.
     * @return delimiters configuration
     */
	Delimiters getDelimiters();
	
	/**
     * Gets the job status with success checking and message.
     * @return job status
     */
	ComputingJobSuccess getStatus();
	
}
