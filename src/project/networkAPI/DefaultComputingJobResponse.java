package project.networkAPI;

/**
 * Default implementation for ComputingJobResponse interface
 * Stores original input/output objects (not just location string)
 * 
 */
public class DefaultComputingJobResponse implements ComputingJobResponse{
	
	private final Input input;
    private final Output output;
    private final Delimiters delimiters;
    private final ComputingJobSuccess status;

    
    /**
     * Creates response with computing job information.
     * @param input the input source used
     * @param output the output destination used
     * @param delimiters the delimiters used
     * @param status the job status
     */
    public DefaultComputingJobResponse(Input input, Output output, Delimiters delimiters, ComputingJobSuccess status) {
        this.input = input;
        this.output = output;
        this.delimiters = delimiters;
        this.status = status;
    }
    
    /**
     * Preserves Input and Output objects instead of location string only
     */
    
    @Override
    public Input getInput() {
    	return input;
    }
    @Override
    public Output getOutput() {
    	return output;
    }
    
    // Returns delimiters that used for formatting
    @Override
    public Delimiters getDelimiters() {
    	return delimiters;
    }
    
    // Provides success checking and message 
    @Override
    public ComputingJobSuccess getStatus() {
    	return status;
    }

	
}
