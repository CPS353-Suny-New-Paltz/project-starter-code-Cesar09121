package project.networkapi;
import java.util.List;

import project.conceptualapi.ComputationAPI;
import project.processapi.DataReadRequest;
import project.processapi.DataReadResponse;
import project.processapi.DataStorageAPI;
import project.processapi.DataWriteRequest;
import project.processapi.DataWriteResponse;
/*
 * Implementation for UserComputingAPI that coordinates factorial computation
 * Uses both data storage component for reading/writing files and the computation component for calculations
 */
public class UserComputingAPIIm implements UserComputingAPI {
	// Dependency for coordination
	private final DataStorageAPI dataStorageAPI;
	private final ComputationAPI computationAPI;
	
	// Constructor for coordination component with dependencies
	public UserComputingAPIIm(DataStorageAPI dataStorageAPI, ComputationAPI computationAPI) {
        this.dataStorageAPI = dataStorageAPI;
        this.computationAPI = computationAPI;
    }
	
	/*
	 * Contains input/output locations and delimiters
	 * Processes real computation job from input file to output file
	 * Should return response with success or failure status
	 */
	@Override
    public ComputingJobResponse submission(ComputingJobRequest request) {
		
	   if (request == null) {
		      throw new IllegalArgumentException("Request cannot be null");
	   }
	   if (request.getInput() == null || request.getOutput() == null) {
		      throw new IllegalArgumentException("Input and output sources required");
	   }
       try {
    	   // Reads input from input location
    	   DataReadResponse readResponse = dataStorageAPI.readInput(new DataReadRequest(
    			   request.getInput().getLocation()));
    	  
    	   
    	   if (!readResponse.getStatus().isSuccess()) {
    		   return failureResponse(request);
    	   }
    	   
    	   // Compute and format results
    	   List<Integer> inputData = readResponse.getData();
    	   StringBuilder results = new StringBuilder();
    	   Delimiters delimiters = request.getDelimitersOrDefault();
    	   
    	   for (int i =0;i< inputData.size();i++) {
    		   
    		   // Asks computation component to calculate the factorial
    		   long factorial = computationAPI.computeFactorial(inputData.get(i));
    		   
    		   // Format as a string (like "3!= 6")
    		   results.append(inputData.get(i))
    		   .append(delimiters.getDelimiterResult())
    		   .append(factorial);
    		   
    		   // Adds delimiter between pairs except the last one
    		   if (i<inputData.size()-1) {
    			   results.append(delimiters.getDelimiters());
    		   }
    	   }
    	   
    	   // Writes formatted output to output file
    	   DataWriteResponse writeResponse = dataStorageAPI.writeOutput(new DataWriteRequest(
    			   request.getOutput().getLocation(),results.toString()));
    	   
    	   // Checks if the writing as successful
    	   if (!writeResponse.getStatus().isSuccess()) {
    		   return failureResponse(request);
    	   }
    	   
    	   // Returns success response if succeeded
    	   return new DefaultComputingJobResponse(
    			   request.getInput(),
    			   request.getOutput(),
    			   delimiters,
    			   ComputingJobSuccess.SUCCESS);
    	   
    	  
       } catch (Exception e) {
    	   return failureResponse(request); // Return failure response if failure
    	   
       }
    }
	
	// Helper method for creating a failure response
	private ComputingJobResponse failureResponse(ComputingJobRequest request) {
		return new DefaultComputingJobResponse(
				request.getInput(),
				request.getOutput(), 
				request.getDelimitersOrDefault(), 
				ComputingJobSuccess.FAILED);
	}
}