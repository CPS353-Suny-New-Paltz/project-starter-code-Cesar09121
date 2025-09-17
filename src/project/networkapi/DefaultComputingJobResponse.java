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
     * 
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
    
    /*
     * Preserves Input and Output objects instead of location string only
     */
  
    public InputSource getInput() {
    	return inputSource;
    }
    
    public OutputSource getOutput() {
    	return outputSource;
    }
    
    
    // Returns delimiters that used for formatting
    public Delimiters getDelimiters() {
    	return delimiters;
    }
    
    // Returns the job completion status
    public ComputingJobSuccess getStatus() {
    	return status;
    }

	
}
