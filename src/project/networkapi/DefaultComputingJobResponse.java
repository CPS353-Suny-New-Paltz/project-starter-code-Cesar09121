package project.networkapi;

/**
 * Default implementation for ComputingJobResponse interface
 * Stores original input/output objects (not just location string)
 * 
 */
public class DefaultComputingJobResponse implements ComputingJobResponse{
	
	private final InputSource inputSource;
    private final OutputSource outputSource;
    private final Delimiters delimiters;
    private final ComputingJobSuccess status;

    
    /**
     * Creates response with computing job information.
     * @param input the input source used
     * @param output the output destination used
     * @param delimiters the delimiters used
     * @param status the job status
     */
    public DefaultComputingJobResponse(InputSource inputSource, OutputSource outputSource, Delimiters delimiters, ComputingJobSuccess status) {
        this.inputSource = inputSource;
        this.outputSource = outputSource;
        this.delimiters = delimiters;
        this.status = status;
    }
    
    /**
     * Returns the input source used in this job.
     * @return input source
     */
    @Override
    public InputSource getInput() {
    	return inputSource;
    }
    
    /**
     * Returns the output destination used in this job.
     * @return output destination
     */
    @Override
    public OutputSource getOutput() {
    	return outputSource;
    }
    
    /**
     * Returns the delimiters used for formatting.
     * @return delimiters configuration
     */
    @Override
    public Delimiters getDelimiters() {
    	return delimiters;
    }
    
    /**
     * Returns the job completion status.
     * @return job status with success checking and message
     */
    @Override
    public ComputingJobSuccess getStatus() {
    	return status;
    }

	
}
