package project.networkAPI;

/*
 * Default implementation for ComputingJobResponse interface
 * Stores original input/output objects (not just location string)
 * 
 */
public class DefaultComputingJobResponse implements ComputingJobResponse{
	
	private final Input input;
    private final Output output;
    private final Delimiters delimiters;
    private final ComputingJobSuccess status;

    // Creates response with computing job information
    public DefaultComputingJobResponse(Input input, Output output, Delimiters delimiters, ComputingJobSuccess status) {
        this.input = input;
        this.output = output;
        this.delimiters = delimiters;
        this.status = status;
    }
    
    /*
     * Preserves Input and Output objects instead of location string only
     */
    public Input getInput() {
    	return input;
    }
    public Output getOutput() {
    	return output;
    }
    
    // Returns delimiters that used for formatting
    public Delimiters getDelimiters() {
    	return delimiters;
    }
    
    // Provides success checking and message 
    public ComputingJobSuccess getStatus() {
    	return status;
    }

	
}
