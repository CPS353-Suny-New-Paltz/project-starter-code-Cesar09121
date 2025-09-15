package project.networkAPI;
import project.annotations.NetworkAPI;

/*
 * Main API interface between user and compute engine
 * Main interface for users to call to submit their computing jobs
 */
@NetworkAPI
public interface UserComputingAPI {
	
	
	/**
     * Submits a computing job for processing
     * ComputingJobRequest contains input, output, and delimiter details
     * Returns response with job status and information
     */
	
	ComputingJobResponse submission (ComputingJobRequest computingJobRequest);

}
